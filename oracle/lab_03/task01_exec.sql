SELECT sector, SumFreeCash(sector)
FROM Company
GROUP BY sector;