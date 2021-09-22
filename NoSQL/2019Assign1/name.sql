#Question 1
UPDATE room
SET price = (price * 1.05);

#Question 2
SELECT * FROM room
WHERE (type = "Double" OR "Family") AND price < 40
ORDER BY price ASC;

#Question 3
SELECT AVG(price) FROM room;

#Question 4
SELECT * FROM room
WHERE roomNo NOT IN (SELECT roomNo FROM booking);

#Question 5
SELECT COUNT(roomNo) FROM room
WHERE hotelNo = (SELECT hotelNo FROM hotel 
					WHERE city = "Washington");
                    
#Question 6
SELECT * FROM booking
WHERE dateTo = "NULL";

#Question 7
SELECT * FROM hotel
WHERE city = "London";

#Question 8
SELECT COUNT(hotelNo) FROM hotel;

#Question 9
SELECT SUM(price) FROM room
WHERE type = "Double";

#Question 10
DELETE FROM booking
WHERE guestNo = (SELECT guestNo FROM guest
					WHERE guestName = "Mary");