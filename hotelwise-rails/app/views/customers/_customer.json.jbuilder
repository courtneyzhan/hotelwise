json.extract! customer, :id, :first_name, :last_name, :dob, :credit_card_number, :credit_card_expiry_month, :credit_card_expiry_year, :created_at, :updated_at
json.url customer_url(customer, format: :json)