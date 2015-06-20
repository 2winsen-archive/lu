INSERT [dbo].[FutureTrade] ([id], [buyer], [seller], [price], [amount], [sentToMiddleware], [resultPayment]) 
VALUES (1, N'JP', N'GOLDMAN', CAST(1000000 AS Decimal(18, 0)), 100, NULL, NULL)

INSERT [dbo].[FutureTrade] ([id], [buyer], [seller], [price], [amount], [sentToMiddleware], [resultPayment]) 
VALUES (2, N'ABAX', N'MAN', CAST(20000 AS Decimal(18, 0)), 25, NULL, NULL)

INSERT [dbo].[FutureTrade] ([id], [buyer], [seller], [price], [amount], [sentToMiddleware], [resultPayment]) 
VALUES (3, N'BMW', N'SEB', CAST(2600000 AS Decimal(18, 0)), 80, NULL, NULL)

INSERT [dbo].[FutureTrade] ([id], [buyer], [seller], [price], [amount], [sentToMiddleware], [resultPayment]) 
VALUES (4, N'NORDEA', N'CAT', CAST(80000 AS Decimal(18, 0)), 40, NULL, NULL)





INSERT [dbo].[future] ([buyer], [seller], [price], [amount], [sentToPayment], [resultPayment]) VALUES (N'JP', N'GOLDMAN', CAST(1000000 AS Decimal(18, 0)), 100, NULL, NULL)
INSERT [dbo].[future] ([buyer], [seller], [price], [amount], [sentToPayment], [resultPayment]) VALUES (N'ABAX', N'MAN', CAST(20000 AS Decimal(18, 0)), 25, NULL, NULL)
INSERT [dbo].[future] ([buyer], [seller], [price], [amount], [sentToPayment], [resultPayment]) VALUES (N'BMW', N'SEB', CAST(2600000 AS Decimal(18, 0)), 80, NULL, NULL)
INSERT [dbo].[future] ([buyer], [seller], [price], [amount], [sentToPayment], [resultPayment]) VALUES (N'NORDEA', N'CAT', CAST(80000 AS Decimal(18, 0)), 40, NULL, NULL)

INSERT [dbo].[swap] ([buyer], [seller], [price], [amount], [sentToPayment], [resultPayment]) VALUES (N'JP', N'GOLDMAN', CAST(1000000 AS Decimal(18, 0)), 100, NULL, NULL)
INSERT [dbo].[swap] ([buyer], [seller], [price], [amount], [sentToPayment], [resultPayment]) VALUES (N'ABAX', N'MAN', CAST(20000 AS Decimal(18, 0)), 25, NULL, NULL)
INSERT [dbo].[swap] ([buyer], [seller], [price], [amount], [sentToPayment], [resultPayment]) VALUES (N'BMW', N'SEB', CAST(2600000 AS Decimal(18, 0)), 80, NULL, NULL)
INSERT [dbo].[swap] ([buyer], [seller], [price], [amount], [sentToPayment], [resultPayment]) VALUES (N'NORDEA', N'CAT', CAST(80000 AS Decimal(18, 0)), 40, NULL, NULL)