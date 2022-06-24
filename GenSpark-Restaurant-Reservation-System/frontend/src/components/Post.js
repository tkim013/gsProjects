import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

import axios from "axios";

import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";

import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import FormControl from "@mui/material/FormControl";

const Post = () => {
  const [resName, setResName] = useState("");
  const [resNumber, setResNumber] = useState("");
  const [dateTime, setDateTime] = useState("");
  const [numberOfGuests, setNumberOfGuests] = useState("");
  const [type, setType] = useState("");

  const navigate = useNavigate();

  const postRes = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/reservation", {
        resName,
        resNumber,
        dateTime,
        numberOfGuests,
        type
      })
      .then((res) => console.log("posting data", res));
      navigate("/home")
      window.location.reload(true);
  };

  return (
    <Container maxWidth="md" sx={{ marginTop: 15, marginBottom: 3 }}>
      <Grid container sx={{ marginBottom: 2 }}>
        <Grid item xs={12}>
          <Typography variant="h2">Add Reservation</Typography>
        </Grid>
      </Grid>
      <Grid container maxWidth="md">
        <Grid item xs={12}>
          <Paper className="form-paper" elevation={10}>
            <Grid container spacing={1}>
              <Grid item xs={12} sm={6}>
                <TextField
                  label="Name"
                  variant="outlined"
                  fullWidth
                  required
                  value={resName}
                  onChange={(e)=> setResName(e.target.value)}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  label="Phone"
                  type="tel"
                  variant="outlined"
                  fullWidth
                  required
                  value={resNumber}
                  onChange={(e)=> setResNumber(e.target.value)}
                />
              </Grid>

              <Grid item xs={12} sm={6}>
                <TextField
                  type="datetime-local"
                  pattern="\d{4}-\d{2}-\d{2}"
                  variant="outlined"
                  fullWidth
                  required
                  value={dateTime}
                  onChange={(e)=> setDateTime(e.target.value)}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  type="number"
                  InputProps={{ inputProps: { min: 0, max: 10 } }}
                  label="Guest"
                  variant="outlined"
                  fullWidth
                  required
                  value={numberOfGuests}
                  onChange={(e)=> setNumberOfGuests(e.target.value)}
                />
              </Grid>
              <Grid item xs={2}>
                <FormControl sx={{ m: 1, minWidth: 120 }} size="medium">
                  <InputLabel id="demo-simple-select-label">Status</InputLabel>
                  <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={type}
                    label="Status"
                    onChange={(e)=>setType(e.target.value)}
                  >
                    <MenuItem value={"PENDING"}>PENDING</MenuItem>
                    <MenuItem value={"CONFIRMED"}>CONFIRMED</MenuItem>
                    <MenuItem value={"ARRIVED"}>ARRIVED</MenuItem>
                    <MenuItem value={"CANCELLED"}>CANCELLED</MenuItem>
                    <MenuItem value={"COMPLETED"}>COMPLETED</MenuItem>
                  </Select>
                </FormControl>
              </Grid>
            </Grid>
            <div className="post-button">
              <Button
                sx={{ marginTop: 1 }}
                type="submit"
                variant="contained"
                fullWidth
                endIcon={<SendIcon color="secondary" />}
                onClick={postRes}
              >
                Add
              </Button>
            </div>
          </Paper>
        </Grid>
      </Grid>
    </Container>
  );
};

export default Post;
