import React from "react";
import HaveQuestions from "./HaveQuestions"
import { Container, Grid, Typography } from "@mui/material";
import {
  Accordion,
  AccordionItem,
  AccordionItemHeading,
  AccordionItemButton,
  AccordionItemPanel,
} from "react-accessible-accordion";

// Demo styles, see 'Styles' section below for some notes on use.
import "react-accessible-accordion/dist/fancy-example.css";

const FaqComponent = () => {
  return (
    <Container maxWidth="md" sx={{ marginTop: 20, marginBottom: 10 }}>
      <Grid container sx={{ marginBottom: 5 }}>
        <Grid item xs={12}>
          <Typography variant="h3">FAQ (Frequently Asked Questions)</Typography>
        </Grid>
      </Grid>
      <Accordion>
        <AccordionItem>
          <AccordionItemHeading>
            <AccordionItemButton>
              Do you accept reservations ?
            </AccordionItemButton>
          </AccordionItemHeading>
          <AccordionItemPanel>
            <Typography variant="h6">Reservation Parties</Typography>
            <p>
              Yes, we accept a limited number of reservations for parties of
              eight or less.
            </p>
            <p>
              We do not take reservations for large parties, Valentine’s Day, or
              Mother’s Day. Parties are seated in order of arrival based on
              party size.
            </p>
            <p>
              Additionally, there are a few other off-peak times when we would
              be happy to accommodate your large party, so please call your
              local restaurant and speak to a manager. We will be happy to
              answer any questions and guide you to the best times to dine!
            </p>
          </AccordionItemPanel>
        </AccordionItem>

        <AccordionItem>
          <AccordionItemHeading>
            <AccordionItemButton>
              Can you accommodate large parties?
            </AccordionItemButton>
          </AccordionItemHeading>
          <AccordionItemPanel>
            <Typography variant="h6">Large Reservation Parties</Typography>
            <p>
              Yes, most of our restaurants have open floor plans which
              accommodate a few large parties (seven or more) for dining at
              quieter times such as for early lunch immediately after we open
              (11 or 11:30AM) or for weekday afternoons (3-5PM). Parties are
              seated in order of arrival based on party size.
            </p>
            <p>
              We do not take large party reservations for dining during busy
              times such as evenings, weekends, holidays or special event
              seasons such as prom or graduation.
            </p>
            <p>
              However, there are a few other off-peak times when we would be
              happy to accommodate your large party, so please call your local
              restaurant and speak to a manager. We will be happy to answer any
              questions for you!
            </p>
          </AccordionItemPanel>
        </AccordionItem>

        <AccordionItem>
          <AccordionItemHeading>
            <AccordionItemButton>
              When is a good time to visit if I don’t have a lot of time to
              wait?
            </AccordionItemButton>
          </AccordionItemHeading>
          <AccordionItemPanel>
            <Typography variant="h6">Any time</Typography>
            <p>
              Arrive early or dine late! Most restaurants are busiest at peak
              meal times; from noon-2PM and 6-8PM on weekdays. Come on a weekday
              before 6PM, you’ll usually get right in without any wait at all!
              Weekends can be busy all day until about 9PM.
            </p>
            <p>
              Our entire menu is always available at our self-seating bar &
              “high top” tables located in our bar areas.
            </p>
            <p>
              Also, everything on our menu including alcohol (where legally
              allowed) can be ordered for pickup online, by phone, or at our
              bakery counters. All locations offer Pickup and most offer
              Delivery, and Curbside To-Go service.
            </p>
          </AccordionItemPanel>
        </AccordionItem>

        <AccordionItem>
          <AccordionItemHeading>
            <AccordionItemButton>
              Reservation Policies, Terms and Conditions
            </AccordionItemButton>
          </AccordionItemHeading>
          <AccordionItemPanel>
            <Typography variant="h6">Terms and Conditions</Typography>
            <p>
              The applicable terms and conditions will vary by location. Be sure
              to check the bottom of your reservation confirmation for the
              specific terms and conditions for the location the reservation
              applies to. Prior to making a reservation, you may search for a
              specific location and view its current policies.
            </p>
          </AccordionItemPanel>
          <hr />
          <AccordionItemPanel>
            <Typography variant="h6">Length of Reservation</Typography>
            <p>
              Reservations for less than one hour are accepted, however a
              minimum charge of one day applies to rentals of less than one
              hours. In some cases, a minimum rental period is required for the
              selected rate to apply.
            </p>
          </AccordionItemPanel>
        </AccordionItem>

        <AccordionItem>
          <AccordionItemHeading>
            <AccordionItemButton>
              How to View, Modify or Cancel
            </AccordionItemButton>
          </AccordionItemHeading>
          <AccordionItemPanel>
            <Typography variant="h6">Cancellation of Reservation</Typography>
            <p>
              On the homepage, select the View/Edit link from the Reservations
              table. Enter your information you want to edit and on the status
              section select "cancelled" from drop down.
            </p>
            <p>
              After any modification or cancellation, we sends a confirmation to
              the e-mail address entered for that reservation
            </p>
          </AccordionItemPanel>
        </AccordionItem>

        <AccordionItem>
          <AccordionItemHeading>
            <AccordionItemButton>
              What is the No Show Fee "NSF" policy?
            </AccordionItemButton>
          </AccordionItemHeading>
          <AccordionItemPanel>
            <Typography variant="h6">No Show Fee</Typography>
            <p>*This non-cancellation fee is assessed when:</p>
            <p>
              the reservation is not cancelled 2 hours prior to the reserved
              time, and the customer is not present within 30 minutes of
              reservation time
            </p>
          </AccordionItemPanel>
          <hr />
          <AccordionItemPanel>
            <Typography variant="h6">Cancellation Policy</Typography>
            <p>
              Our cancellation policies vary, depending on the type of
              reservation you've made:
            </p>
            <p>
              <strong>
                Was your credit card charged for the reservation time on our
                website?
              </strong>
            </p>
            <p>
              If you wish to cancel before your check-in time, you must cancel
              and request a refund online. Once cancelled, we will credit you
              within approximately seven business days. Credits will be applied
              to the same card with which you prepaid.
            </p>
            <p>
              <strong>
                Did you make a reservation without providing credit card
                information?
              </strong>
            </p>
            <p>
              If you make a reservation without a credit card and you no longer
              need to make a reservation, please cancel your reservation as soon
              as possible before your scheduled check-in time.
            </p>
          </AccordionItemPanel>
        </AccordionItem>
      </Accordion>
      <HaveQuestions />
    </Container>
  );
};

export default FaqComponent;
