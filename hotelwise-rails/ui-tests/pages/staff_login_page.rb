require File.join(File.dirname(__FILE__), "abstract_page.rb")

class StaffLoginPage < AbstractPage

  def initialize(driver)
    super(driver, "") # <= TEXT UNIQUE TO THIS PAGE
  end

  def enter_username(username)
    driver.find_element(:id, "username").send_keys(username)
  end

  def enter_password(password)
    driver.find_element(:id, password).send_keys(password)
  end

  def click_sign_in
    driver.find_element(:id, "sign_in").click
  end

end
