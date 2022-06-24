/* eslint-disable */

import React from 'react';
import { Outlet } from 'react-router';
import Navbar from '../Navbar';


const WithNav = () => {
  return (
    <>
    <Navbar />
    <Outlet />
    </>
  )
}

export default WithNav