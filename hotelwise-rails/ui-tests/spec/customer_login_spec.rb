load File.dirname(__FILE__) + '/../test_helper.rb'

describe "New Specification" do
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

  it "Customer can login " do
    driver.find_element(:link_text, "Login").click
    sleep 0.5
    driver.find_element(:id, "email").send_keys("jsu@email.com")
    driver.find_element(:id, "password").send_keys("asdf")
    driver.find_element(:id, "sign_in").click
    expect(driver.page_source).to include("John")
  end

  it "Customer can log out" do
    driver.find_element(:link_text, "Login").click
    sleep 0.5
    driver.find_element(:id, "email").send_keys("jsu@email.com")
    driver.find_element(:id, "password").send_keys("asdf")
    driver.find_element(:id, "sign_in").click
    expect(driver.page_source).to include("John")
    driver.find_element(:link_text, "Log out").click
    sleep 0.5
    expect(driver.page_source).to include("Login")
  end

  it "Register customer" do
    driver.find_element(:link_text, "create account first").click
    sleep 0.5
    driver.find_element(:id, "customer_first_name").send_keys("Mary")
    driver.find_element(:id, "customer_last_name").send_keys("Sue")
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "customer_dob_1i")).select_by(:text, "1997")
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "customer_dob_2i")).select_by(:text, "May")
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "customer_dob_3i")).select_by(:text, "5")
    driver.find_element(:id, "customer_address_1").send_keys("1 Generic Street")
    driver.find_element(:id, "customer_address_2").send_keys("Suburb")
    driver.find_element(:id, "customer_email").send_keys("msue4@email.com")
    driver.find_element(:id, "customer_password").send_keys("secret")
    driver.find_element(:name, "commit").click
    sleep 0.5
    expect(driver.page_source).to include("Customer was successfully created.")
  end

  it "Staff login" do
    visit("/admin")
    sleep 0.5
    driver.find_element(:id, "username").send_keys("staff")
    driver.find_element(:id, "password").send_keys("password")
    driver.find_element(:id, "sign_in").click
  end

  it "Book a SOV Room" do
    driver.find_element(:link_text, "Login").click
    sleep 0.5
    driver.find_element(:id, "email").send_keys("jsu@email.com")
    driver.find_element(:id, "password").send_keys("asdf")
    driver.find_element(:id, "sign_in").click
    driver.find_element(:id, "numofadult").send_keys("1")
    driver.find_element(:id, "numofchildren").send_keys("0")
    driver.find_element(:id, "depart").send_keys("2017-01-13")
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "roomtype")).select_by(:text, "Single with Ocean View")
    driver.find_element(:id, "check").click
    sleep 0.5
    expect(driver.page_source).to include("Available Rooms")
    driver.find_element(:name, "commit").click
    expect(driver.page_source).to include("Booking was successfully created.")
  end

  it "Double Book the Single with Ocean View room" do
    driver.find_element(:link_text, "Login").click
    sleep 0.5
    driver.find_element(:id, "email").send_keys("jsu@email.com")
    driver.find_element(:id, "password").send_keys("asdf")
    driver.find_element(:id, "sign_in").click
    driver.find_element(:id, "numofadult").send_keys("1")
    driver.find_element(:id, "numofchildren").send_keys("0")
    driver.find_element(:id, "depart").send_keys("2017-01-13")
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "roomtype")).select_by(:text, "Single with Ocean View")
    driver.find_element(:id, "check").click
    sleep 0.5
    driver.find_element(:name, "commit").click
    expect(driver.page_source).to include("Booking was successfully created.")
    visit("/search")
    sleep 0.5
    driver.find_element(:id, "numofadult").send_keys("1")
    driver.find_element(:id, "numofchildren").send_keys("0")
    driver.find_element(:id, "depart").send_keys("2017-01-13")
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "roomtype")).select_by(:text, "Single with Ocean View")
    driver.find_element(:id, "check").click
    sleep 0.5
    expect(driver.page_source).to include("No rooms available")
  end

  it "Single room has only one room available" do
    driver.find_element(:link_text, "Login").click
    sleep 0.5
    driver.find_element(:id, "email").send_keys("jsu@email.com")
    driver.find_element(:id, "password").send_keys("asdf")
    driver.find_element(:id, "sign_in").click
    driver.find_element(:id, "numofadult").send_keys("1")
    driver.find_element(:id, "numofchildren").send_keys("0")
    driver.find_element(:id, "depart").send_keys("2017-01-13")
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "roomtype")).select_by(:text, "Single")
    driver.find_element(:id, "check").click
    sleep 0.5
    driver.find_element(:name, "commit").click
    expect(driver.page_source).to include("Booking was successfully created.")
  end

  it "Family Rooms fully booked" do
    driver.find_element(:link_text, "Login").click
    sleep 0.5
    driver.find_element(:id, "email").send_keys("jsu@email.com")
    driver.find_element(:id, "password").send_keys("asdf")
    driver.find_element(:id, "sign_in").click
    driver.find_element(:id, "numofadult").send_keys("1")
    driver.find_element(:id, "numofchildren").send_keys("0")
    driver.find_element(:id, "depart").send_keys("2017-01-13")
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "roomtype")).select_by(:text, "Family")
    driver.find_element(:id, "check").click
    sleep 0.5
    driver.find_element(:name, "commit").click
    expect(driver.page_source).to include("Booking was successfully created.")
    visit("/search")
    sleep 0.5
    driver.find_element(:id, "numofadult").send_keys("1")
    driver.find_element(:id, "numofchildren").send_keys("0")
    driver.find_element(:id, "depart").send_keys("2017-01-13")
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "roomtype")).select_by(:text, "Family")
    driver.find_element(:id, "check").click
    sleep 0.5
    driver.find_element(:name, "commit").click
    expect(driver.page_source).to include("Booking was successfully created.")
  end

  it "Double Room, both rooms available" do
    driver.find_element(:link_text, "Login").click
    sleep 0.5
    driver.find_element(:id, "email").send_keys("jsu@email.com")
    driver.find_element(:id, "password").send_keys("asdf")
    driver.find_element(:id, "sign_in").click
    driver.find_element(:id, "numofadult").send_keys("1")
    driver.find_element(:id, "numofchildren").send_keys("0")
    driver.find_element(:id, "depart").send_keys("2017-01-13")
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "roomtype")).select_by(:text, "Single")
    driver.find_element(:id, "check").click
    sleep 0.5
    driver.find_element(:name, "commit").click
    expect(driver.page_source).to include("Booking was successfully created.")
    visit("/search")
    sleep 0.5
    driver.find_element(:id, "numofadult").send_keys("1")
    driver.find_element(:id, "numofchildren").send_keys("0")
    driver.find_element(:id, "depart").send_keys("2017-01-13")
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "roomtype")).select_by(:text, "Single")
    driver.find_element(:id, "check").click
    sleep 0.5
    driver.find_element(:name, "commit").click
    expect(driver.page_source).to include("Booking was successfully created.")
  end

end
