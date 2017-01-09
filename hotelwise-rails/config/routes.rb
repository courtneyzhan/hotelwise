Rails.application.routes.draw do
  
  resources :users
  resources :customers
  resources :bookings
  resources :rooms
  
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  root to: 'welcome#home'

  get "reset" => "welcome#reset" # reset the database
  
  get "search" => 'bookings#new'
  get "bookings" => 'bookings#index'
  get '/log_in' => 'welcome#customer_log_in'
  post '/log_in' => 'welcome#customer_log_in'
  get '/register' => 'customers#new'
  get '/staff_log_in' => 'welcome#staff_log_in'
  get '/admin' => 'welcome#staff_log_in'
  post '/staff_log_in' => 'welcome#staff_log_in'
  get '/log_out' => 'welcome#customer_log_out'
    
  post '/bookings/check' => 'bookings#check'
  get '/single-ov' => 'pages#customer_register'
  get '/single' => 'pages#customer_register'
  get '/double' => 'pages#customer_register'
  get '/family' => 'pages#not_available'
  get '/confirmation' => 'pages#confirmation'
  get '/receipt' => 'pages#receipt'

end
