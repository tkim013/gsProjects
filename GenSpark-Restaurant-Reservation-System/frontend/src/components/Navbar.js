import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

import {
  AppBar,
  Button,
  Tab,
  Tabs,
  Toolbar,
  Typography,
  useMediaQuery,
  useTheme,
} from "@mui/material";

import AddBusinessRoundedIcon from "@mui/icons-material/AddBusinessRounded";
import DrawerComponent from "./DrawerComponent";

const Navbar = () => {
  const [value, setValue] = useState("home");
  const navigate = useNavigate();

  const theme = useTheme();
  console.log(theme);
  const isMatch = useMediaQuery(theme.breakpoints.down("md"));
  console.log(isMatch);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  const handleLogout = () => {
    navigate("/")
  };



  return (
    <>
      <AppBar sx={{ background: "#000000"}}>
        <Toolbar>
          <AddBusinessRoundedIcon sx={{ transform: "scale(2)", color: "#097969" }} />
          {isMatch ? (
            <>
              <Typography sx={{ fontSize: "2rem", paddingLeft: "10%" }}>
                Restaurant Reservation
              </Typography>
              <DrawerComponent />
            </>
          ) : (
            <>
              <Tabs
                sx={{ marginLeft: "auto" }}
                indicatorColor="secondary"
                textColor="inherit"
                value={value}
                onChange={handleChange}
              >
                <Tab label="Home" value="home" onClick={() => navigate("/home")}/>
                <Tab label="Reservation" value="post" onClick={() => navigate("/post")}/>
                <Tab label="Faq" value="faq" onClick={() => navigate("/faq")}/>
                <Tab label="Contact" value="contact" onClick={() => navigate("/contact")}/>
              </Tabs>
              <Button sx={{ marginLeft: "auto", backgroundColor: "#097969" }} variant="contained"
              onClick={handleLogout}
              >
                Logout
              </Button>
              {/* //signup */}
              {/* <Button sx={{ marginLeft: "10px", backgroundColor: "#097969" }} variant="contained"
              onClick={() => navigate("/")}
              >
                SignUp
              </Button> */}
            </>
          )}
        </Toolbar>
      </AppBar>
    </>
  );
};

export default Navbar;