import React from "react";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import {
  Button,
  Grid,
  TextField,
  Container,
  FormControl,
  InputLabel,
  Select,
} from "@mui/material";

const HaveQuestions = () => {
  const [anchorEl, setAnchorEl] = React.useState(null);

  const open = Boolean(anchorEl);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <Container sx={{ marginTop: 10 }}>
      <Button
      sx={{'&:hover':{backgroundColor: "secondary.main" } }}
        id="basic-button"
        variant="contained"
        aria-controls={open ? "basic-menu" : undefined}
        aria-haspopup="true"
        aria-expanded={open ? "true" : undefined}
        onClick={handleClick}
      >
        Have more Questions ?
      </Button>
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          "aria-labelledby": "basic-button",
        }}
      >
        <MenuItem>
          <React.Fragment>
            <Grid container spacing={3}>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  id="firstName"
                  name="firstName"
                  label="Full Name"
                  fullWidth
                  autoComplete="given-name"
                  variant="standard"
                />
              </Grid>
             
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  id="email"
                  name="email"
                  label="Email"
                  type="email"
                  fullWidth
                  autoComplete="email"
                  variant="standard"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  id="phone"
                  name="phone"
                  label="phone"
                  fullWidth
                  autoComplete="phone"
                  variant="standard"
                />
              </Grid>
              <Grid item xs={12}>
                <FormControl sx={{ m: 0, minWidth: "100%" }} size="md">
                  <InputLabel id="demo-simple-select-label">
                    Visit/Order Type*
                  </InputLabel>
                  <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    // value={age}
                    label="Status"
                    // onChange={handleChange}
                  >
                    <MenuItem value={0}>Dine-in</MenuItem>
                    <MenuItem value={1}>Take-out</MenuItem>
                    <MenuItem value={2}>Delivery</MenuItem>
                  </Select>
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <TextField
                  id="date"
                  name="date"
                  type="date"
                  fullWidth
                  variant="standard"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  id="time"
                  name="time"
                  type="time"
                  fullWidth
                  autoComplete="time"
                  variant="standard"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  id="comment"
                  name="comment"
                  label="comment"
                  fullWidth
                  multiline
                  rows={5}
                  autoComplete="comment"
                  variant="standard"
                />
              </Grid>
              <Button
                variant="contained"
                fullWidth
                color="primary"
                size="medium"
                sx={{
                  MarginTop: 0,
                  "&:hover": { backgroundColor: "secondary.main" },
                }}
                onClick={handleClose}
              >
                Send
              </Button>
            </Grid>
          </React.Fragment>
        </MenuItem>
      </Menu>
    </Container>
  );
};

export default HaveQuestions;
