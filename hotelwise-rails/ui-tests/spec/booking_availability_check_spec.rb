load File.dirname(__FILE__) + '/../test_helper.rb'

describe "Test Suite" do
  include TestHelper

  before(:all) do
    @driver = $browser = Selenium::WebDriver.for(browser_type)
    @driver.navigate.to(site_url)
  end

  before(:each) do
    visit("/reset")
  end

  after(:all) do
    @driver.quit unless debugging?
  end


  it "Book a SOV Room" do
    search_page = SearchPage.new(browser)
    search_page.click_login_link("Login")
    sleep 0.5
    login_page = LoginPage.new(browser)
    login_page.enter_email("jsu@email.com")
    login_page.enter_password("asdf")
    login_page.click_sign_in_button
    search_page.enter_num_of_adult("1")
    search_page.enter_num_of_children("0")
    search_page.enter_departure_date("2017-01-13")
    search_page.select_room_type("Single with Ocean View")
    search_page.click_search
    sleep 1
    expect(driver.page_source).to include("Available Rooms")
    available_rooms_list_page = AvailableRoomsListPage.new(browser)
    available_rooms_list_page.click_create_booking
    expect(driver.page_source).to include("Confirmation")
  end

  it "Double Book the Single with Ocean View room" do
    search_page = SearchPage.new(browser)
    search_page.click_login_link("Login")
    sleep 0.5
    login_page = LoginPage.new(browser)
    login_page.enter_email("jsu@email.com")
    login_page.enter_password("asdf")
    login_page.click_sign_in_button
    search_page.enter_num_of_adult("1")
    search_page.enter_num_of_children("0")
    search_page.enter_departure_date("2017-01-13")
    search_page.select_room_type("Single with Ocean View")
    search_page.click_search
    sleep 1
    expect(driver.page_source).to include("Available Rooms")
    available_rooms_list_page = AvailableRoomsListPage.new(browser)
    available_rooms_list_page.click_create_booking
    expect(driver.page_source).to include("Confirmation")
    visit("/search")
    sleep 1
    search_page.enter_num_of_adult("1")
    search_page.enter_num_of_children("0")
    search_page.enter_departure_date("2017-01-13")
    search_page.select_room_type("Single with Ocean View")
    search_page.click_search
    sleep 1
    expect(driver.page_source).to include("No rooms available")
  end

  it "Single room has only one room available" do
    search_page = SearchPage.new(browser)
    search_page.click_login_link("Login")
    sleep 0.5
    login_page = LoginPage.new(browser)
    login_page.enter_email("jsu@email.com")
    login_page.enter_password("asdf")
    login_page.click_sign_in_button
    search_page.enter_num_of_adult("1")
    search_page.enter_num_of_children("0")
    search_page.enter_departure_date("2017-01-13")
    search_page.select_room_type("Single")
    search_page.click_search
    sleep 0.5
    available_rooms_list_page = AvailableRoomsListPage.new(browser)
    available_rooms_list_page.click_create_booking
    expect(driver.page_source).to include("Confirmation")
  end

  it "Family Rooms fully booked" do
    search_page = SearchPage.new(browser)
    search_page.click_login_link("Login")
    sleep 0.5
    login_page = LoginPage.new(browser)
    login_page.enter_email("jsu@email.com")
    login_page.enter_password("asdf")
    login_page.click_sign_in_button
    search_page.enter_num_of_adult("1")
    search_page.enter_num_of_children("0")
    search_page.enter_departure_date("2017-01-13")
    search_page.select_room_type("Family")
    search_page.click_search
    sleep 0.5
    available_rooms_list_page = AvailableRoomsListPage.new(browser)
    available_rooms_list_page.click_create_booking
    expect(driver.page_source).to include("Confirmation")
    visit("/search")
    sleep 0.5
    search_page.enter_num_of_adult("1")
    search_page.enter_num_of_children("0")
    search_page.enter_departure_date("2017-01-13")
    search_page.select_room_type("Family")
    search_page.click_search
    sleep 0.5
    available_rooms_list_page.click_create_booking
    expect(driver.page_source).to include("Confirmation")
  end

  it "Double Room, both rooms available" do
    search_page = SearchPage.new(browser)
    search_page.click_login_link("Login")
    sleep 0.5
    login_page = LoginPage.new(browser)
    login_page.enter_email("jsu@email.com")
    login_page.enter_password("asdf")
    login_page.click_sign_in_button
    search_page.enter_num_of_adult("1")
    search_page.enter_num_of_children("0")
    search_page.enter_departure_date("2017-01-13")
    search_page.select_room_type("Double")
    search_page.click_search
    sleep 0.5
    available_rooms_list_page = AvailableRoomsListPage.new(browser)
    available_rooms_list_page.click_create_booking
    expect(driver.page_source).to include("Confirmation")
    visit("/search")
    sleep 0.5
    search_page.enter_num_of_adult("1")
    search_page.enter_num_of_children("0")
    search_page.enter_departure_date("2017-01-13")
    search_page.select_room_type("Double")
    search_page.click_search
    sleep 0.5
    available_rooms_list_page.click_create_booking
    expect(driver.page_source).to include("Confirmation")
  end


end
