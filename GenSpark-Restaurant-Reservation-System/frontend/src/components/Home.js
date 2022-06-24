/* eslint-disable */
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import axios from "axios";

import { Container, Grid, TableFooter, Typography } from "@mui/material";

import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import { styled, useTheme } from "@mui/material/styles";
import SendIcon from "@mui/icons-material/Send";
import TextField from "@mui/material/TextField";
import swal from "sweetalert";

import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";

import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import FormControl from "@mui/material/FormControl";

//testing page
// import Pagination from "@mui/material/Pagination";
import TablePagination from "@mui/material/TablePagination";

import IconButton from "@mui/material/IconButton";
import FirstPageIcon from "@mui/icons-material/FirstPage";
import KeyboardArrowLeft from "@mui/icons-material/KeyboardArrowLeft";
import KeyboardArrowRight from "@mui/icons-material/KeyboardArrowRight";
import LastPageIcon from "@mui/icons-material/LastPage";
import PropTypes from "prop-types";

import "../App.css";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 20,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: "#dddddd",
  },
  // hide last border
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "#097969",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

const Home = () => {
  const [reservations, setReservations] = useState([]);

  //testing pagination
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  // const [pageSize, setPageSize] = useState(5)
  // const [pageNumber, setPageNumber] = useState(1)
  // const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const [searchTerm, setSearchTerm] = useState("");

  const [resName, setResName] = useState("");
  const [resNumber, setResNumber] = useState("");
  const [dateTime, setDateTime] = useState("");
  const [numberOfGuests, setNumberOfGuests] = useState("");
  const [resId, setResId] = useState(null);
  const [type, setType] = useState("");

  const [open, setOpen] = React.useState(false);

  const navigate = useNavigate();

 
  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const baseURL = "http://localhost:8080/api";

  useEffect(() => {
    axios.get(`${baseURL}/reservation`).then((response) => {
      setReservations(response.data);
    });
  }, []);

 
  const handleOpen = (index) => {
    console.log("id: " + reservations[index].resId);

    let item = reservations[index];
    setDateTime(item.dateTime);
    setResName(item.resName);
    setResNumber(item.resNumber);
    setType(item.type);
    setResId(item.resId);
    setNumberOfGuests(item.numberOfGuests);
    setOpen(true);
  };

  function deletePost(id) {
    axios.delete(`${baseURL}/reservation/${id}`).then((response) => {
      setReservations(null);
    });
    swal({
      title: "Deleting Reservation...",
      text: `reservation with id: ${id} deleted`,
      icon: "error",
      dangerMode: true,
    });
    setTimeout(() => {
      window.location.reload(true);
    }, 1000);
    // navigate("/home");
  }

  function updatePost(id) {
    let item = { resName, resNumber, type, resId, numberOfGuests, dateTime };
    console.warn("item", item);
    axios
      .put(`${baseURL}/reservation/${id}`, item)
      .then((res) => console.warn("posting data", res));
    setOpen(false);
    setTimeout(() => {
      window.location.reload(true);
    }, 1000);
    // navigate("/home")
  }

  return (
    <>
      <img className="img-home" src="images/home-banner2.jpg" alt="home" />
      <Container sx={{ marginTop: 8, marginBottom: 10 }}>
        <Grid container sx={{ objectFit: "cover" }}>
          <Grid item xs={12}>
            <Typography variant="h2" sx={{ fontWeight: "bold" }}>
              Reservations List
            </Typography>
          </Grid>
        </Grid>

        <Grid
          container
          maxWidth="lg"
          sx={{
            display: "flex",
            marginTop: 10,
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          <Grid
            item
            sx={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <Box
              sx={{
                width: "100%",
                maxWidth: "100%",
              }}
            >
              <TextField
                fullWidth
                label="Search Name.."
                id="fullWidth"
                onChange={(e) => setSearchTerm(e.target.value)}
                value={searchTerm}
              />
            </Box>
          </Grid>
        </Grid>

        <Grid container sx={{ marginTop: 5 }}>
          <Grid item xs={12}>
            <TableContainer component={Paper} elevation={15}>
              <Table sx={{ minWidth: 700 }} aria-label="customized table">
                <TableHead>
                  <TableRow>
                    <StyledTableCell>ID</StyledTableCell>
                    <StyledTableCell align="right">Name</StyledTableCell>
                    <StyledTableCell align="right">Phone</StyledTableCell>
                    <StyledTableCell align="right">Date</StyledTableCell>
                    <StyledTableCell align="right">Guest</StyledTableCell>
                    <StyledTableCell align="right">Status</StyledTableCell>
                    <StyledTableCell align="right">
                      Edit Reservation
                    </StyledTableCell>
                    <StyledTableCell align="right">
                      Delete Reservation
                    </StyledTableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {reservations
                    .filter((reservation) => {
                      if (searchTerm === "") {
                        return reservation;
                      } else if (
                        reservation.resName
                          .toLowerCase()
                          .includes(searchTerm.toLowerCase())
                      ) {
                        return reservation;
                      }
                    })
                    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                    .map((reservation, index) => (
                      <StyledTableRow key={index}>
                        <StyledTableCell
                          className="table-cell"
                          component="th"
                          scope="row"
                        >
                          {reservation.resId}
                        </StyledTableCell>
                        <StyledTableCell className="table-cell" align="right">
                          {reservation.resName}
                        </StyledTableCell>
                        <StyledTableCell className="table-cell" align="right">
                          {reservation.resNumber}
                        </StyledTableCell>
                        <StyledTableCell className="table-cell" align="right">
                          {reservation.dateTime}
                        </StyledTableCell>
                        <StyledTableCell className="table-cell" align="right">
                          {reservation.numberOfGuests}
                        </StyledTableCell>
                        <StyledTableCell className="table-cell" align="right">
                          {reservation.type}
                        </StyledTableCell>
                        <StyledTableCell className="table-cell" align="right">
                          <Button
                            type="submit"
                            variant="none"
                            endIcon={<EditIcon color="secondary" />}
                            onClick={() => handleOpen(`${index}`)}
                          >
                            Edit
                          </Button>
                        </StyledTableCell>

                        <Modal
                          open={open}
                          onClose={handleClose}
                          aria-labelledby="modal-modal-title"
                          aria-describedby="modal-modal-description"
                        >
                          <Box sx={style}>
                            <Paper className="form-paper" elevation={10}>
                              <Grid container spacing={1} sx={{ padding: 1 }}>
                                <Grid item xs={6} sm={6}>
                                  <input
                                    className="form-control"
                                    placeholder="Name"
                                    type="text"
                                    value={resName}
                                    onChange={(e) => setResName(e.target.value)}
                                  />
                                </Grid>
                                <Grid item xs={6} sm={6}>
                                  <input
                                    className="form-control"
                                    placeholder="phone"
                                    type="tel"
                                    value={resNumber}
                                    onChange={(e) =>
                                      setResNumber(e.target.value)
                                    }
                                  />
                                </Grid>
                                <Grid item xs={6} sm={6}>
                                  <input
                                    className="form-control"
                                    placeholder="Date"
                                    type="datetime-local"
                                    pattern="\d{4}-\d{2}-\d{2}"
                                    value={dateTime}
                                    onChange={(e) =>
                                      setDateTime(e.target.value)
                                    }
                                  />
                                </Grid>
                                <Grid item xs={6}>
                                  <input
                                    className="form-control"
                                    placeholder="Guest"
                                    type="number"
                                    min="0"
                                    value={numberOfGuests}
                                    onChange={(e) =>
                                      setNumberOfGuests(e.target.value)
                                    }
                                  />
                                </Grid>
                                <Grid item xs={12}>
                                  <FormControl
                                    sx={{ m: 0, minWidth: 120 }}
                                    size="small"
                                  >
                                    <InputLabel id="demo-simple-select-label">
                                      Status
                                    </InputLabel>
                                    <Select
                                      labelId="demo-simple-select-label"
                                      id="demo-simple-select"
                                      value={type}
                                      label="Status"
                                      onChange={(e) => setType(e.target.value)}
                                    >
                                      <MenuItem
                                        value={"PENDING"}
                                        sx={{ color: "#FFBF00" }}
                                      >
                                        Pending
                                      </MenuItem>
                                      <MenuItem
                                        value={"CONFIRMED"}
                                        sx={{ color: "#097969" }}
                                      >
                                        Confirmed
                                      </MenuItem>
                                      <MenuItem
                                        value={"ARRIVED"}
                                        sx={{ color: "#16EE1E" }}
                                      >
                                        Arrived
                                      </MenuItem>
                                      <MenuItem
                                        value={"CANCELLED"}
                                        sx={{ color: "#DC143C" }}
                                      >
                                        Cancelled
                                      </MenuItem>
                                      <MenuItem
                                        value={"COMPLETED"}
                                        sx={{ color: "#16EE1E" }}
                                      >
                                        Completed
                                      </MenuItem>
                                    </Select>
                                  </FormControl>
                                </Grid>
                              </Grid>
                              <div
                                className="post-button"
                                sx={{ marginTop: 2 }}
                              >
                                <Button
                                  type="submit"
                                  variant="contained"
                                  value="Update"
                                  fullWidth
                                  endIcon={<SendIcon color="secondary" />}
                                  onClick={() =>
                                    updatePost(`${reservation.resId}`)
                                  }
                                >
                                  done
                                </Button>
                              </div>
                            </Paper>
                          </Box>
                        </Modal>

                        <StyledTableCell className="table-cell" align="right">
                          <Button
                            id="delete"
                            type="submit"
                            variant="none"
                            endIcon={<DeleteIcon color="error" />}
                            onClick={() => deletePost(`${reservation.resId}`)}
                          >
                            Delete
                          </Button>
                        </StyledTableCell>
                      </StyledTableRow>
                    ))}
                </TableBody>
                <TableFooter>
                  <StyledTableRow>
                    <TablePagination
                      rowsPerPageOptions={[5, 10, 50]}
                      count={reservations.length}
                      rowsPerPage={rowsPerPage}
                      page={page}
                      onPageChange={handleChangePage}
                      onRowsPerPageChange={handleChangeRowsPerPage}
                    />
                  </StyledTableRow>
                </TableFooter>
              </Table>
            </TableContainer>
          </Grid>
        </Grid>
      </Container>
    </>
  );
};

export default Home;
