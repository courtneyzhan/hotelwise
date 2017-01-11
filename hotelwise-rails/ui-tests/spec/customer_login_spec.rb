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
    search_page = SearchPage.new(browser)
    search_page.click_login_link("Login")
    sleep 0.5
    login_page = LoginPage.new(browser)
    login_page.enter_email("jsu@email.com")
    login_page.enter_password("asdf")
    login_page.click_sign_in_button
    expect(driver.page_source).to include("John")
  end

  it "Customer can log out" do
    search_page = SearchPage.new(browser)
    search_page.click_login_link("Login")
    sleep 0.5
    login_page = LoginPage.new(browser)
    login_page.enter_email("jsu@email.com")
    login_page.enter_password("asdf")
    login_page.click_sign_in_button
    expect(driver.page_source).to include("John")
    search_page.click_log_out("Log out")
    sleep 0.5
    expect(driver.page_source).to include("Login")
  end

  it "Register customer" do
    search_page = SearchPage.new(browser)

    search_page.click_create_account("create account first")
    sleep 0.5
    register_customer_page = RegisterCustomerPage.new(browser)
    register_customer_page.enter_first_name("Mary")
    register_customer_page.enter_last_name("Sue")
    register_customer_page.select_dob_year("1997")
    register_customer_page.select_dob_month("May")
    register_customer_page.select_dob_day("5")
    register_customer_page.enter_address_1("1 Generic Street")
    register_customer_page.enter_address_2("Suburb")
    register_customer_page.enter_email("msue4@email.com")
    register_customer_page.enter_password("secret")
    register_customer_page.click_create
    sleep 0.5
    expect(driver.page_source).to include("Customer was successfully created.")
  end

end
