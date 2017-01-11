load File.dirname(__FILE__) + '/../test_helper.rb'

describe "Test Suite" do
  include TestHelper

  before(:all) do
    @driver = $browser = Selenium::WebDriver.for(browser_type)
    @driver.navigate.to(site_url)
  end

  after(:all) do
    @driver.quit unless debugging?
  end

  before(:each) do
    visit("/reset")
  end

  it "Staff login" do
    visit("/admin")
    sleep 0.5
    staff_login_page = StaffLoginPage.new(browser)
    staff_login_page.enter_username("staff")
    staff_login_page.enter_password("password")
    staff_login_page.click_sign_in
    sleep 0.5
    expect(driver.page_source).to include("New Booking")
  end

end
