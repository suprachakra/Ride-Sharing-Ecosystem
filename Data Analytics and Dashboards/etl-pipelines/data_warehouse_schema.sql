// SQL schema for your data warehouse tables (as defined in your ERD)

-- USERS Table
CREATE TABLE USERS (
    user_id UUID PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    rating DECIMAL(3,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- DRIVERS Table
CREATE TABLE DRIVERS (
    driver_id UUID PRIMARY KEY,
    user_id UUID REFERENCES USERS(user_id) ON DELETE CASCADE,
    license_number VARCHAR(50) UNIQUE NOT NULL,
    rating DECIMAL(3,2),
    status VARCHAR(20) CHECK (status IN ('ACTIVE', 'INACTIVE', 'SUSPENDED')) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- VEHICLES Table
CREATE TABLE VEHICLES (
    vehicle_id UUID PRIMARY KEY,
    driver_id UUID REFERENCES DRIVERS(driver_id) ON DELETE SET NULL,
    make VARCHAR(50),
    model VARCHAR(50),
    license_plate VARCHAR(20) UNIQUE NOT NULL,
    capacity INTEGER CHECK (capacity > 0),
    type VARCHAR(20) CHECK (type IN ('SEDAN', 'SUV', 'VAN', 'ELECTRIC')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- BOOKINGS Table
CREATE TABLE BOOKINGS (
    booking_id UUID PRIMARY KEY,
    rider_id UUID REFERENCES USERS(user_id),
    driver_id UUID REFERENCES DRIVERS(driver_id) ON DELETE SET NULL,
    trip_type VARCHAR(20) CHECK (trip_type IN ('STANDARD', 'POOL', 'LUXURY')) NOT NULL,
    status VARCHAR(20) CHECK (status IN ('PENDING', 'ACCEPTED', 'COMPLETED', 'CANCELLED')) NOT NULL,
    pickup_lat DECIMAL(9,6) NOT NULL,
    pickup_lon DECIMAL(9,6) NOT NULL,
    dropoff_lat DECIMAL(9,6) NOT NULL,
    dropoff_lon DECIMAL(9,6) NOT NULL,
    pickup_time TIMESTAMP,
    dropoff_time TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- BOOKING_RIDER Table
CREATE TABLE BOOKING_RIDER (
    booking_id UUID REFERENCES BOOKINGS(booking_id) ON DELETE CASCADE,
    rider_id UUID REFERENCES USERS(user_id),
    share_amount DECIMAL(10,2),
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (booking_id, rider_id)
);

-- FARES Table
CREATE TABLE FARES (
    fare_id UUID PRIMARY KEY,
    booking_id UUID REFERENCES BOOKINGS(booking_id) ON DELETE CASCADE,
    base_fare DECIMAL(10,2),
    distance_charge DECIMAL(10,2),
    waiting_charge DECIMAL(10,2),
    surge_multiplier DECIMAL(3,2),
    discount DECIMAL(10,2),
    total_fare DECIMAL(10,2),
    fare_per_rider DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- PAYMENTS Table
CREATE TABLE PAYMENTS (
    payment_id UUID PRIMARY KEY,
    booking_id UUID REFERENCES BOOKINGS(booking_id) ON DELETE CASCADE,
    rider_id UUID REFERENCES USERS(user_id),
    amount DECIMAL(10,2),
    payment_method VARCHAR(20) CHECK (payment_method IN ('CARD', 'WALLET', 'UPI', 'CASH')),
    status VARCHAR(20) CHECK (status IN ('PENDING', 'SUCCESS', 'FAILED', 'REFUNDED')),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    transaction_ref VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- DISPATCH Table
CREATE TABLE DISPATCH (
    dispatch_id UUID PRIMARY KEY,
    booking_id UUID REFERENCES BOOKINGS(booking_id) ON DELETE CASCADE,
    driver_id UUID REFERENCES DRIVERS(driver_id),
    vehicle_id UUID REFERENCES VEHICLES(vehicle_id),
    estimated_arrival_time INTEGER,
    dispatch_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) CHECK (status IN ('ASSIGNED', 'EN_ROUTE', 'ARRIVED', 'CANCELLED')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- COMPLIANCE_LOGS Table
CREATE TABLE COMPLIANCE_LOGS (
    log_id UUID PRIMARY KEY,
    booking_id UUID REFERENCES BOOKINGS(booking_id) ON DELETE SET NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- SURGE_LOGS Table
CREATE TABLE SURGE_LOGS (
    log_id UUID PRIMARY KEY,
    region VARCHAR(100),
    surge_multiplier DECIMAL(3,2),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- DRIVER_RATINGS Table
CREATE TABLE DRIVER_RATINGS (
    rating_id UUID PRIMARY KEY,
    booking_id UUID REFERENCES BOOKINGS(booking_id),
    driver_id UUID REFERENCES DRIVERS(driver_id),
    rider_id UUID REFERENCES USERS(user_id),
    rating INTEGER CHECK (rating BETWEEN 1 AND 5),
    comments TEXT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- RIDE_METRICS Table
CREATE TABLE RIDE_METRICS (
    metric_id UUID PRIMARY KEY,
    date DATE UNIQUE,
    total_rides INTEGER,
    pooled_rides INTEGER,
    avg_wait_time DECIMAL(5,2),
    avg_fare DECIMAL(10,2),
    avg_driver_rating DECIMAL(3,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
