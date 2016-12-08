# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

room_1 = Room.create(room_number: "100", room_type: "Single", price: 25, floor: "1", facility: "WiFi", description: "room with single bed")

room_2 = Room.create(room_number: "110", room_type: "Single with Ocean View", price: 25, floor: "1", facility: "WiFi", description: "room with single bed with Ocean View")

room_3 = Room.create(room_number: "320", room_type: "Double", price: 25, floor: "3", facility: "WiFi", description: "room with double bed")

customer_1 = Customer.create(first_name: "John", last_name: "Doe", dob: 19990325, credit_card_number: "123456789", credit_card_expiry_month: "01", credit_card_expiry_year: "01")

customer_1 = Customer.create(first_name: "Mary", last_name: "Smith", dob: 20010920, credit_card_number: "348592712", credit_card_expiry_month: "01", credit_card_expiry_year: "01")

customer_1 = Customer.create(first_name: "Karen", last_name: "Green", dob: 20000101, credit_card_number: "84237201", credit_card_expiry_month: "01", credit_card_expiry_year: "01")

booking_1 = Booking.create(room_id: "1", customer_id: "1", check_in_date: 20160101, check_out_date: 20160103)

booking_2 = Booking.create(room_id: "2", customer_id: "1", check_in_date: 20160101, check_out_date: 20160103)

booking_3 = Booking.create(room_id: "2", customer_id: "2", check_in_date: 20160102, check_out_date: 20160103)

booking_1 = Booking.create(room_id: "3", customer_id: "3", check_in_date: 20160101, check_out_date: 20160103)