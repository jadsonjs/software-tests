
@ReservationTest

Feature: Reservation in a hotel

  Scenario: make a reservation
  Given The user click to confirm the reservation
  When Complete the reservation
  Then send a confirmation e-mail
  """
      Hello,

      You reservation has been confirmed.

      Best Regards,
      Admin System
      """