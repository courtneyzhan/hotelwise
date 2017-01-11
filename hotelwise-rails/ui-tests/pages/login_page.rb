require File.join(File.dirname(__FILE__), "abstract_page.rb")

class LoginPage < AbstractPage

  def initialize(driver)
    super(driver, "") # <= TEXT UNIQUE TO THIS PAGE
  end

  def enter_email(email)
    driver.find_element(:id, "email").send_keys(email)
  end

  def enter_password(password)
    driver.find_element(:id, "password").send_keys(password)
  end

  def click_sign_in_button
    driver.find_element(:id, "sign_in").click
  end

end
