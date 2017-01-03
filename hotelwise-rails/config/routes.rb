Rails.application.routes.draw do
  
  resources :customers
  resources :bookings
  resources :rooms
  
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  root to: 'welcome#home'
  get "search" => 'pages#index'
  get "bookings" => 'bookings#index'
  get '/log_in' => 'welcome#customer_log_in'
  post '/log_in' => 'welcome#customer_log_in'
  get '/register' => 'welcome#customer_register'
  get '/staff_log_in' => 'welcome#staff_log_in'
  post '/staff_log_in' => 'welcome#staff_log_in'
  
  
  post '/availability_check' => 'pages#check'
  get '/single-ov' => 'pages#customer_register'
  get '/single' => 'pages#customer_register'
  get '/double' => 'pages#customer_register'
  get '/family' => 'pages#not_available'
  get '/confirmation' => 'pages#confirmation'
  get '/receipt' => 'pages#receipt'
end
