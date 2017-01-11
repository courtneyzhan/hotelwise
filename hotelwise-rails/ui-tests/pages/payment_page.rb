require File.join(File.dirname(__FILE__), "abstract_page.rb")

class PaymentPage < AbstractPage

  def initialize(driver)
    super(driver, "") # <= TEXT UNIQUE TO THIS PAGE
  end

  def enter_credit_card_number(credit_card_number)
    driver.find_element(:id, "credit_card_number").send_keys(credit_card_number)
  end

  def enter_credit_card_verification(credit_card_verification)
    driver.find_element(:id, "credit_card_verification").send_keys(credit_card_verification)
  end

  def enter_credit_card_expiry_date(credit_card_expiry)
    driver.find_element(:id, "credit_card_expiry").send_keys(credit_card_expiry)
  end

  def click_continue
    driver.find_element(:id, "continue").click
  end

end
