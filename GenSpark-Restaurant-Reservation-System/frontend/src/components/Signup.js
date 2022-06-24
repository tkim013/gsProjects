import React from "react";

import { useNavigate } from "react-router-dom";

import {
  Grid,
  Paper,
  Avatar,
  Typography,
  TextField,
  Button,
  Container,
} from "@mui/material";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";

import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import FormControl from "@mui/material/FormControl";

const Signup = ({
  email,
  setEmail,
  password,
  setPassword,
  username,
  setUserName,
  postData,

  role,
  setRole,
}) => {
  console.log(email);
  const paperStyle = { padding: "30px 20px", width: 300, margin: "20px auto" };
  const headerStyle = { margin: 0 };

  const navigate = useNavigate();

  return (
    <Container
      component="main"
      maxWidth="xs"
      sx={{ marginTop: 13, marginBottom: 10 }}
    >
      <Grid>
        <Paper elevation={20} style={paperStyle}>
          <Grid align="center">
            <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
              <AddCircleOutlineOutlinedIcon />
            </Avatar>
            <h2 style={headerStyle}>Sign Up</h2>
            <Typography variant="caption" gutterBottom>
              Please fill this form to create an account !
            </Typography>
          </Grid>
          <form onSubmit={() => navigate("/")}>
            <TextField
              fullWidth
              label="Username"
              placeholder="Enter username"
              type="text"
              required
              value={username}
              onChange={(e) => setUserName(e.target.value)}
            />
            <TextField
              fullWidth
              label="Email"
              type="email"
              placeholder="Enter your email"
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <TextField
              fullWidth
              label="Password"
              type="password"
              placeholder="Enter your password"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {/* <TextField
              fullWidth
              label="Roles"
              type="text"
              placeholder="Enter your role"
              required
              value={role}
              onChange={(e)=> setRole(e.target.value)}
            /> */}

            <Grid item xs={12} sx={{ marginTop: 1, marginBottom: 1 }}>
              <FormControl sx={{ m: 0, minWidth: 120 }} size="small">
                <InputLabel id="demo-simple-select-label">Roles</InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={role}
                  multiple="true"
                  label="ROLES"
                  onChange={(e) => setRole(e.target.value)}
                >
                  <MenuItem value={"USER"} sx={{ color: "#FFBF00" }}>
                    user
                  </MenuItem>
                  <MenuItem value={"MOD"} sx={{ color: "#DC143C" }}>
                    mod
                  </MenuItem>
                  <MenuItem value={"ADMIN"} sx={{ color: "#097969" }}>
                    admin
                  </MenuItem>
                </Select>
              </FormControl>
            </Grid>

            <Button
              sx={{ "&:hover": { backgroundColor: "secondary.main" } }}
              type="submit"
              variant="contained"
              color="primary"
              onClick={postData}
            >
              Sign up
            </Button>
          </form>
        </Paper>
      </Grid>
    </Container>
  );
};

export default Signup;
