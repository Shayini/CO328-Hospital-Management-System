INSERT INTO admin (name, username, password, age, contact_no, email, address, active) VALUES
('Admin', 'admin', '1234', 26, 0778608719, 'admin@gmail.com', 'Kandy', false);


INSERT INTO doctor (name, age, username, password, contact_no, email, address, department, specialization, active) VALUES
('Doctor1', 30, 'doctor1', '1234', 0771234567, 'doctor1@gmail.com', 'Kandy', 'Neurology', 'Surgeon', false),
('Doctor2', 32, 'doctor2', '1234', 0774657463, 'doctor2@gmail.com', 'Jaffna', 'Neurology', 'Surgeon', false),
('Doctor3', 33, 'doctor3', '1234', 0775867493, 'doctor3@gmail.com', 'Colombo', 'Cardiology', 'Surgeon', false),
('Doctor4', 30, 'doctor4', '1234', 0777968472, 'doctor4@gmail.com', 'Trincomalee', 'Cardiology', 'Surgeon', false),
('Doctor5', 30, 'doctor5', '1234', 0777968404, 'doctor5@gmail.com', 'Baticallo', 'Neurology', 'Surgeon', false),
('Doctor6', 60, 'doctor6', '1234', 0777968404, 'doctor6@gmail.com', 'Jaffna', 'Dental', 'Surgeon', false),
('Doctor7', 50, 'doctor7', '1234', 0777968404, 'doctor7@gmail.com', 'Kandy', 'Dental', 'Surgeon', false),
('Doctor8', 45, 'doctor8', '1234', 0777968404, 'doctor8@gmail.com', 'Baticallo', 'Dermatology', 'Surgeon', false),
('Doctor9', 35, 'doctor9', '1234', 0777968404, 'doctor9@gmail.com', 'Jaffna', 'Dermatology', 'Surgeon', false),
('Doctor10', 40, 'doctor10', '1234', 0777968404, 'doctor10@gmail.com', 'Kandy', 'Ophthalmology', 'Surgeon', false);


INSERT INTO user (name, username, password, age, contact_no, email, address, active) VALUES
('User1', 'user1', 'Thaksha17', 26, 0774857653, 'user1@gmail.com', 'Kandy', false),
('User2', 'user2', 'Thaksha17', 22, 0718079586, 'user2@gmail.com', 'Vavuniya', false),
('User3', 'user3', 'Thaksha17', 19, 0770675843, 'user3@gmail.com', 'Colombo', false),
('User4', 'user4', 'Thaksha17', 55, 0776093821, 'user4@gmail.com', 'Galle', false);


INSERT INTO schedule (date, from_time, to_time, doctorid) VALUES
('2020-08-28', '08:30:00', '11:00:00', 1),
('2020-08-30', '08:00:00', '12:30:00', 2),
('2020-08-29', '08:00:00', '15:00:00', 3),
('2020-09-01', '08:30:00', '15:00:00', 3),
('2020-09-01', '08:00:00', '15:00:00', 4),
('2020-09-02', '09:00:00', '12:00:00', 3);


INSERT INTO booking (date, time, department, doctorid, scheduleid, userid, email, contact_no) VALUES
('2020-08-28', '08:30:00', 'Neurology', 1, 1, 3, 'user3@gmail.com', 0770675843),
('2020-08-28', '09:30:00', 'Neurology', 1, 1, 1, 'user1@gmail.com', 0774857653),
('2020-09-01', '08:30:00', 'Cardiology', 3, 4, 2, 'user2@gmail.com', 0718079586);
