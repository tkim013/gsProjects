import React, { useState } from "react";
import { Link } from "react-router-dom";
import {
  Drawer,
  IconButton,
  List,
  ListItemButton,
  ListItemIcon,
  ListItemText,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
const pages = ["Home", "Post", "Faq", "Contact"];

const DrawerComponent = () => {
  const [openDrawer, setOpenDrawer] = useState(false);



  return (
    <React.Fragment>
      <Drawer
      PaperProps={{
        sx: {
          backgroundColor: "#000000",
          '&:hover':{backgroundColor: "white" } 
        }
      }}
        anchor="left"
        open={openDrawer}
        onClose={() => setOpenDrawer(false)}
      >
        <List >
          {pages.map((page, index) => (
            <ListItemButton key={index} sx={{backgroundColor: '#000000'}}>
              <ListItemIcon >
                <ListItemText >
                <Link to={`/${page}`} style={{color: '#097969', textDecoration: "none", fontWeight: 'bold' }}>{page}</Link>
                  </ListItemText>
              </ListItemIcon>
            </ListItemButton>
          ))}
        </List>
      </Drawer>
      <IconButton
        sx={{ color: "#097969", marginLeft: "auto" }}
        onClick={() => setOpenDrawer(!openDrawer)}
      >
        <MenuIcon color="white" />
      </IconButton>
    </React.Fragment>
  );
};

export default DrawerComponent;