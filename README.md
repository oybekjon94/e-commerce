## 🛒 e-commerce 🛍
### 😉 This application is not completed yet. Currently, I am working on it.
### ✨ Features

### Built With🛠
- [Kotlin](https://kotlinlang.org/) - Most widely used programming language for Android development.
- [Coroutines﻿](https://kotlinlang.org/docs/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [GSON](https://github.com/google/gson) - Gson is also widely used in Kotlin and Java development for JSON serialization and deserialization.
- [GSON Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) - A Converter which uses Gson for serialization to and from JSON.
- [OkHttp3](https://github.com/square/okhttp) - For implementing interceptor, logging and mocking web server.
- [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Dependency injection](https://developer.android.com/training/dependency-injection) - Dependency injection (DI) is a technique widely used in programming and well suited to Android development.
   - Reusability of code
   - Ease of refactoring
   - Ease of testing

### Other Concepts included
- RecyclerView
- CardView
- ImageSlider
- Fragments
- Passing data between activities, fragments
- SearchView
- SwipeRefresh
- Navigation
...

### 📸 Screenshots
  Onboarding            |  Sign in & Sign up   
:-------------------------:|:-------------------------:
  ![image](https://github.com/oybekjon94/e-commerce/assets/91370134/10c72e60-403f-428f-9f63-a279c420fb36) | ![image](https://github.com/oybekjon94/e-commerce/assets/91370134/c116abd3-060c-46c5-8b0d-2ff80a769b43)  
   Home            |  Search  | Wishlist
 ![Screenshot_3](https://github.com/oybekjon94/e-commerce/assets/91370134/7d8f78d2-f8bf-460b-8850-05e3ddcef3cc) | ![Screenshot_4](https://github.com/oybekjon94/e-commerce/assets/91370134/82c8a1f1-7510-44f9-8c72-bc614eaea9ed) 
 Filter | Detail 
![Screenshot_5](https://github.com/oybekjon94/e-commerce/assets/91370134/a2d794f3-bd74-43d1-8d2c-121b540577b2) | ![Screenshot_6](https://github.com/oybekjon94/e-commerce/assets/91370134/d5e7be42-86f4-4281-904e-03ea936413de)
Wishlist | not completed yet
![Screenshot_7](https://github.com/oybekjon94/e-commerce/assets/91370134/ff025569-2f82-4b4b-9d27-ff4ff4bec603) |


### Package Structure
````
|
├── app
|    └── App
|
├── common
|    └── Constants
|
├── data
|    ├──  api
|    |    ├── auth
|    |    |     ├── dto
|    |    |     |    ├── AuthResponse
|    |    |     |    ├── SignInRequest
|    |    |     |    ├── SignUpRequest
|    |    |     |    └── UserDto
|    |    |     └── AuthApi
|    |    |
|    |    └── product
|    |          ├── dto
|    |          |    ├── Banner
|    |          |    ├── Category
|    |          |    ├── Detail
|    |          |    ├── HomeResponse
|    |          |    ├── Product
|    |          |    ├── Section
|    |          |    └── SectionType
|    |          |    
|    |          ├── paging
|    |          |    └── ProductPagingSource
|    |          └── ProductApi
|    |
|    ├── repo
|    |    ├── AuthRepositoryImpl
|    |    └── ProductRepositoryImpl
|    |
|    └── store
|         ├── BaseStore
|         ├── OnboardedStore
|         ├── RecentsStore
|         ├── TokenStore
|         └── UserStore
|
├── di
|    ├── ApiModule
|    ├── DataModule
|    ├── NetworkModule
|    └── RepositoryModule
|
├── domain
|    ├── model
|    |    ├── Destination
|    |    ├── ProductQuery
|    |    └── User
|    └── repo
|         ├── AuthRepository
|         └── ProductRepository
|
├── presentation
|    ├── categories
|    |    ├── CategoriesAdapter
|    |    ├── CategoriesFragment
|    |    └── CategoriesViewModel
|    |
|    ├── detail
|    |    ├── DetailFragment
|    |    ├── DetailViewModel
|    |    ├── ImageAdapter
|    |    └── RelatedAdapter
|    |
|    ├── filter
|    |    ├── FilterFragment
|    |    └── FilterViewModel
|    |
|    ├── home
|    |    ├── adapter
|    |    |    ├── BannerAdapter
|    |    |    ├── HomeCategoryAdapter
|    |    |    ├── HorizontalAdapter
|    |    |    ├── SectionAdapter
|    |    |    └── VerticalAdapter
|    |    ├── HomeFragment
|    |    └── HomeViewModel
|    |
|    ├── main
|    |    ├── MainActivity
|    |    └── MainViewModel
|    |
|    ├── onboarding
|    |    ├── OnBoardingAdapter
|    |    ├── OnBoardingViewModel
|    |    └── OnboardingFragment
|    |
|    ├── orders
|    |    ├── OrdersFragment
|    |    ├── ..
|    |  
|    ├── products
|    |    ├── ProductViewHolder
|    |    ├── ProductViewModel
|    |    ├── ProductsAdapter
|    |    └── ProductsFragment
|    |
|    ├── profile
|    |    ├── ProfileFragment
|    |    ├── ..
|    |
|    ├── search
|    |    ├── adapter
|    |    |    ├── RecentAdapter
|    |    |    ├── RecentAdapter
|    |    |    └── SearchProductsAdapter
|    |    ├── SearchFragment
|    |    └── SearchViewModel
|    |
|    ├── sign_in
|    |    ├── SignInFragment
|    |    └── SignInViewModel
|    |
|    ├── sign_up
|    |    ├── SignUpFragment
|    |    └── SignUpViewModel
|    └── wishlist
|         ├── WishlistFragment
|         └── WishlistViewModel
|   
└── utils
     ├── BaseFragment
     ├── Functions
     ├── HorizontalMarginItemDecoration
     └── SingleLiveEvent
````

### 👨‍🔧 Architecture
This app uses [MVVM](https://medium.com/@bansooknam/android-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%EB%B9%84%EA%B5%90-mvp-mvvm-svc-1-f24e5f338523) (Model View View-Model) architecture.

![image](https://github.com/oybekjon94/e-commerce/assets/91370134/2e8f5b49-3830-4e7f-b837-368ec3b52f50)
