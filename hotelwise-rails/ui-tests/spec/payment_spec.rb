load File.dirname(__FILE__) + '/../test_helper.rb'

describe "Payment" do
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


  it "Book and Pay" do
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
    sleep 0.5
    available_rooms_list_page = AvailableRoomsListPage.new(browser)
    available_rooms_list_page.click_create_booking
    expect(driver.page_source).to include("Confirmation")
    payment_page = PaymentPage.new(browser)
    payment_page.enter_credit_card_number("12345678")
    payment_page.enter_credit_card_verification("1324")
    payment_page.enter_credit_card_expiry_date("2017-12-31")
    payment_page.click_continue
    sleep 0.5
    expect(driver.page_source).to include("Receipt")
  end



end
