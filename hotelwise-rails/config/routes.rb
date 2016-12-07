Rails.application.routes.draw do
  
  resources :rooms
  
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  root to: 'pages#index'
  post '/availability_check' => 'pages#check'
  get '/single-ov' => 'pages#customer_register'
  get '/single' => 'pages#customer_register'
  get '/double' => 'pages#customer_register'
  get '/family' => 'pages#not_available'
  get '/confirmation' => 'pages#confirmation'
  get '/receipt' => 'pages#receipt'
end
