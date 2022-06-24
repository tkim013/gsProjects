import React from "react";
// import { SendEmail } from './SendEmail';
import { FormControl, InputLabel, Input, Button, Container } from "@mui/material";

function Contact() {
  return (
      <Container componet="div" sx={{marginTop: 8}}>
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        margin: 20,
        padding: 20,
      }}
    >
      <form style={{ width: "100%" }}>
        <h1>Contact Form</h1>

        <FormControl margin="normal" fullWidth>
          <InputLabel htmlFor="name">Name</InputLabel>
          <Input id="name" type="text" />
        </FormControl>

        <FormControl margin="normal" fullWidth>
          <InputLabel htmlFor="email">Email</InputLabel>
          <Input id="email" type="email" />
        </FormControl>

        <FormControl margin="normal" fullWidth>
          <InputLabel htmlFor="message">Message</InputLabel>
          <Input id="message" multiline rows={10} />
        </FormControl>

        <Button variant="contained" fullWidth color="primary" size="medium" sx={{MarginTop:0, '&:hover':{backgroundColor: "secondary.main" }}}>
          Send
        </Button>
      </form>
    </div>
    </Container>
  );
}

export default Contact;
