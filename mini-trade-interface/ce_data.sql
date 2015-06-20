INSERT [dbo].[CashEquityTrade] ([id], [buyer], [seller], [price], [amount], [sentToMiddleware], [resultPayment], [resultRisk], [resultExchange]) 
VALUES (1, N'JP', N'GOLDMAN', CAST(1000000 AS Decimal(18, 0)), 100, NULL, NULL, NULL, NULL)

INSERT [dbo].[CashEquityTrade] ([id], [buyer], [seller], [price], [amount], [sentToMiddleware], [resultPayment], [resultRisk], [resultExchange]) 
VALUES (2, N'ABAX', N'MAN', CAST(20000 AS Decimal(18, 0)), 25, NULL, NULL, NULL, NULL)

INSERT [dbo].[CashEquityTrade] ([id], [buyer], [seller], [price], [amount], [sentToMiddleware], [resultPayment], [resultRisk], [resultExchange]) 
VALUES (3, N'BMW', N'SEB', CAST(2600000 AS Decimal(18, 0)), 80, NULL, NULL, NULL, NULL)

INSERT [dbo].[CashEquityTrade] ([id], [buyer], [seller], [price], [amount], [sentToMiddleware], [resultPayment], [resultRisk], [resultExchange]) 
VALUES (4, N'NORDEA', N'CAT', CAST(80000 AS Decimal(18, 0)), 40, NULL, NULL, NULL, NULL)