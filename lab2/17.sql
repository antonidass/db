INSERT INTO Balance (free_cash, stocks_amount, turnover, total_assets, income)
SELECT free_cash, stocks_amount, turnover * 0.0001, total_assets, income / 10
FROM Balance
WHERE income > 95;
    
SELECT * FROM Balance;