# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

room_type_single = RoomType.create(:name => "Single", :price => 25, facility: "<ul><li>WiFi</li><li>One Bed</li></ul>", description: "room with single bed")
room_type_single_ov = RoomType.create(:name => "Single with Ocean View", :price => 30, facility: "<ul><li>WiFi</li><li>One Bed with Ocean View</li></ul>", description: "room with single bed and ocean view")
room_type_double = RoomType.create(:name => "Double", :price => 40, facility: "<ul><li>WiFi</li><li>Two Beds</li></ul>", description: "room with single bed")
room_type_family = RoomType.create(:name => "Family", :price => 50, facility: "<ul><li>WiFi</li><li>Two Beds + Cot</li></ul>", description: "room with single bed")

room_1 = Room.create(room_number: "100", floor: "1", :room_type_id => room_type_single.id)
room_2 = Room.create(room_number: "110", floor: "1", :room_type_id => room_type_single_ov.id)
room_3 = Room.create(room_number: "320", floor: "3", :room_type_id => room_type_double.id)
room_4 = Room.create(room_number: "321", floor: "3", :room_type_id => room_type_family.id)

customer_1 = Customer.create(first_name: "John", last_name: "Su", dob: 19990325, address_1: "1 Generic Street", address_2: "Suburb", email: "jsu@email.com", password: "asdf", credit_card_number: "123456789", credit_card_expiry_month: "01", credit_card_expiry_year: "01")
customer_2 = Customer.create(first_name: "Mary", last_name: "Smith", dob: 20010920, address_1: "2 Generic Street", address_2: "Suburb", email: "a@b.com", password: "secret", credit_card_number: "348592712", credit_card_expiry_month: "01", credit_card_expiry_year: "01")
customer_3 = Customer.create(first_name: "Karen", last_name: "Green", dob: 20000101, address_1: "4 Generic Street", address_2: "Suburb", email: "kgree@email.com", password: "secret", credit_card_number: "84237201", credit_card_expiry_month: "01", credit_card_expiry_year: "01")

booking_1 = Booking.create(room_id: "1", customer_id: "1", check_in_date: 20160101, check_out_date: 20160103)
booking_2 = Booking.create(room_id: "2", customer_id: "1", check_in_date: 20160101, check_out_date: 20160103)
booking_3 = Booking.create(room_id: "2", customer_id: "2", check_in_date: 20160102, check_out_date: 20160103)
booking_4 = Booking.create(room_id: "3", customer_id: "3", check_in_date: 20160101, check_out_date: 20160103)


user_1 = User.create(login: "staff", password: "password")