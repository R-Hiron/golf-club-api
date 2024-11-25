# Golf Club API

## Features

- **Member Management**
    - Add new members
    - Retrieve member details
    - Search for members by:
        - Name
        - Membership type
        - Phone number
        - Tournament start date

- **Tournament Management**
    - Add new tournaments
    - Retrieve tournament details
    - Search for tournaments by:
        - Start date
        - Location
    - Retrieve all members in a specific tournament

### 1. Clone the Repository
   ```bash
   git clone https://github.com/R-Hiron/golf-club-api.git
   cd golf-club-api
   ```
### 2. Build the Application
   ```bash
   mvn clean install
   ```
### 3. Run with Docker Compose
```bash
docker-compose up --build
```
### 4. Access the API
   The API will be available at http://localhost:8080.

You can test the API using tools like Postman or curl.

## API Endpoints
### Member Endpoints
- Get all members
    - GET /api/members
- Get member by ID    
  - GET /api/members/{id}
- Create a new member
  - POST /api/members
- Search for members
  - GET /api/members/search?name={name}
  - GET /api/members/search?type={type}
  - GET /api/members/search?phoneNumber={phoneNumber}
  - GET /api/members/search?tournamentStartDate={startDate}
### Tournament Endpoints
- Get all tournaments 
  - GET /api/tournaments
- Get tournament by ID
  - GET /api/tournaments/{id}
- Create a new tournament 
  - POST /api/tournaments
- Search for tournaments
  - GET /api/tournaments/search?startDate={startDate}
  - GET /api/tournaments/search?location={location}
  - GET /api/tournaments/{id}/members
  
