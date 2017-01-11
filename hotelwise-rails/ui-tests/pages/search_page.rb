require File.join(File.dirname(__FILE__), "abstract_page.rb")

class SearchPage < AbstractPage

  def initialize(driver)
    super(driver, "") # <= TEXT UNIQUE TO THIS PAGE
  end

  def click_login_link(link_text)
    driver.find_element(:link_text, link_text).click
  end

  def click_log_out(link_text)
    driver.find_element(:link_text, link_text).click
  end

  def click_create_account(link_text)
    driver.find_element(:link_text, link_text).click
  end

  def enter_num_of_adult(numofadult)
    driver.find_element(:id, "numofadult").send_keys(numofadult)
  end

  def enter_num_of_children(numofchildren)
    driver.find_element(:id, "numofchildren").send_keys(numofchildren)
  end

  def enter_departure_date(depart)
    driver.find_element(:id, "depart").send_keys(depart)
  end

  def select_room_type(roomtype)
    Selenium::WebDriver::Support::Select.new(driver.find_element(:id, "roomtype")).select_by(:text, roomtype)
  end

  def click_search
    driver.find_element(:id, "check").click
  end

end
