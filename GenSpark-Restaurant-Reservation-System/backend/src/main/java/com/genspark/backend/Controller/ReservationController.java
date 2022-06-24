package com.genspark.backend.Controller;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @GetMapping("/reservation")
  public List<Reservation> getReservations() {
    return this.reservationService.getAllReservation();
  }

  @GetMapping("/usertest")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }

//  @GetMapping("/reservationp")
//  public ResponseEntity<List<Reservation>> getAllReservations(
//          @RequestParam(defaultValue = "0") Integer pageNo,
//          @RequestParam(defaultValue = "10") Integer pageSize,
//          @RequestParam(defaultValue = "resId") String sortBy)
//  {
//    List<Reservation> list = reservationService.getAllReservation(pageNo, pageSize, sortBy);
//    return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
//  }

  @GetMapping("/reservation/{reservationID}")
  @PreAuthorize("hasRole('ADMIN')")
  public Reservation getReservation(@PathVariable String reservationID) {
    return this.reservationService.getReservationById(Long.parseLong(reservationID));
  }

  @PostMapping("/reservation/add")
//  @PostAuthorize("hasRole('USER')")
  public Reservation addReservation(@RequestBody Reservation reservation) {
    return this.reservationService.addReservation(reservation);
  }

  @PutMapping("/reservation/{reservationID}")
  @PostAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public Reservation updateReservation(@RequestBody Reservation reservation, @PathVariable Long reservationID) {
    return this.reservationService.updateReservation(reservation, reservationID);
  }

  @DeleteMapping("/reservation/{reservationID}")
  @PostAuthorize("hasRole('ADMIN')")
  public String deleteReservation(@PathVariable String reservationID)
  {
    return this.reservationService.deleteReservationById(Long.parseLong(reservationID));
  }
}
