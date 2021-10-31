ALTER TABLE Company 
ADD (
     shadow CHAR(3) NOT NULL CHECK (shadow IN ('YES', 'NO'))
);