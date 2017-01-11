require File.join(File.dirname(__FILE__), "abstract_page.rb")

class AvailableRoomsListPage < AbstractPage

  def initialize(driver)
    super(driver, "") # <= TEXT UNIQUE TO THIS PAGE
  end

  def click_create_booking
    driver.find_element(:name, "commit").click
  end

end
