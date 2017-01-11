require File.join(File.dirname(__FILE__), "abstract_page.rb")

class RegisterCustomerPage < AbstractPage

  def initialize(driver)
    super(driver, "") # <= TEXT UNIQUE TO THIS PAGE
  end

  def enter_first_name(customer_first_name)
    driver.find_element(:id, "customer_first_name").send_keys(customer_first_name)
  end

  def enter_last_name(customer_last_name)
    driver.find_element(:id, "customer_last_name").send_keys(customer_last_name)
  end

  def select_dob_year(customer_dob_1i)
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "customer_dob_1i")).select_by(:text, customer_dob_1i)
  end

  def select_dob_month(customer_dob_2i)
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "customer_dob_2i")).select_by(:text, customer_dob_2i)
  end

  def select_dob_day(customer_dob_3i)
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "customer_dob_3i")).select_by(:text, customer_dob_3i)
  end

  def enter_address_1(customer_address_1)
    driver.find_element(:id, "customer_address_1").send_keys(customer_address_1)
  end

  def enter_address_2(customer_address_2)
    driver.find_element(:id, "customer_address_2").send_keys(customer_address_2)
  end

  def enter_email(customer_email)
    driver.find_element(:id, "customer_email").send_keys(customer_email)
  end

  def enter_password(customer_password)
    driver.find_element(:id, "customer_password").send_keys(customer_password)
  end

  def click_create
    driver.find_element(:name, "commit").click
  end

end
