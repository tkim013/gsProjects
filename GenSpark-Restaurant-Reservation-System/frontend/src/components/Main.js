import React, { useState } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
//testings for
import axios from "axios";

//file imports
import Login from "./Login";
import Signup from "./Signup";
import Home from "./Home";
import Post from "./Post";
// import Navbar from "./Navbar";
import Contact from "./Contact";
import FaqComponent from "./FaqComponent";

//material imports
import { createTheme, ThemeProvider } from "@mui/material/styles";
import WithNav from "./Navigation rendering/WithNav";
import WithOut from "./Navigation rendering/WithOut";
// import Testing from "./Testing";

const theme = createTheme({
  palette: {
    primary: {
      main: "#000000", //black
      light: "", //green
    },
    secondary: {
      main: "#097969", //green
    },
    warning: {
      main: "#fff",
    },
  },
  typography: {
    fontFamily: "Garamond",
  },
});

const Main = () => {
  const [username, setUserName] = useState("");
  const [role, setRole] = useState([]);
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");

  const [passwordLog, setPasswordLog] = useState("");
  const [userLog, setUserLog] = useState("");

  const navigate = useNavigate();

  // const postData = (e) => {
  //   e.preventDefault();
  //   axios
  //     .post("http://localhost:8080/api/user", {
  //       userName,
  //       userNumber,
  //       password,
  //       email,
  //     })
  //     .then((res) => console.warn("posting data", res));
  //   navigate("/");
  // };
  const postData = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/auth/signup", {
        username,
        role,
        password,
        email,
      })
      .then((res) => { 
        const data = res.data.roles
        console.warn("posting signup data", res)
        console.warn("posting signup roles", data)
        // console.warn(data.includes("ROLE_ADMIN") + "includes ROLE_ADMIN")
        // if( data.includes("ROLE_ADMIN")){
        //   navigate("/faq");
        // } else {
        //   navigate("/home");
        // }
      });
    navigate("/");
  };

  const postLogin = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/auth/signin", {
        username: userLog,
        password: passwordLog,
      })
      .then((res) =>{ 
        const data = res.data.roles
        console.warn("posting Login data", res.data)
        // console.warn(data.includes("ROLE_ADMIN") + "includes ROLE_ADMIN")
        if( data.includes("ROLE_ADMIN")){
          // navigate("/faq");
        } else {
          // navigate("/home");
        }
      });
    // navigate("/home");
  };

  return (
    <>
      <ThemeProvider theme={theme}>
        {/* {isShown && <Navbar />} */}
        <Routes>
          <Route element={<WithOut />}>
            <Route
              path="/"
              element={
                <Login
                  passwordLog={passwordLog}
                  userLog={userLog}
                  setUserLog={setUserLog}
                  setPasswordLog={setPasswordLog}
                  postLogin={postLogin}
                />
              }
            />
            <Route
              path="signup"
              element={
                <Signup
                  username={username}
                  password={password}
                  email={email}
                  setEmail={setEmail}
                  setPassword={setPassword}
                  setUserName={setUserName}
                  postData={postData}
                  role={role}
                  setRole={setRole}
                />
              }
            />
          </Route>

          <Route element={<WithNav />}>
            <Route path="home" element={<Home />} />
            <Route path="post" element={<Post />} />
            <Route path="faq" element={<FaqComponent />} />
            <Route path="contact" element={<Contact />} />
          </Route>
        </Routes>
      </ThemeProvider>
    </>
  );
};

export default Main;
