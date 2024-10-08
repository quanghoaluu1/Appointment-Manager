USE [master]
GO
/****** Object:  Database [Workshop2]    Script Date: 7/6/2024 9:18:16 PM ******/
CREATE DATABASE [Workshop2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Workshop2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.HOAQUANG\MSSQL\DATA\Workshop2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Workshop2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.HOAQUANG\MSSQL\DATA\Workshop2_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [Workshop2] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Workshop2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Workshop2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Workshop2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Workshop2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Workshop2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Workshop2] SET ARITHABORT OFF 
GO
ALTER DATABASE [Workshop2] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Workshop2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Workshop2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Workshop2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Workshop2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Workshop2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Workshop2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Workshop2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Workshop2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Workshop2] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Workshop2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Workshop2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Workshop2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Workshop2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Workshop2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Workshop2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Workshop2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Workshop2] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Workshop2] SET  MULTI_USER 
GO
ALTER DATABASE [Workshop2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Workshop2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Workshop2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Workshop2] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Workshop2] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Workshop2] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Workshop2] SET QUERY_STORE = ON
GO
ALTER DATABASE [Workshop2] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Workshop2]
GO
/****** Object:  Table [dbo].[Appointments]    Script Date: 7/6/2024 9:18:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Appointments](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[appointmentDate] [date] NULL,
	[appointmentTime] [time](7) NULL,
	[purpose] [varchar](255) NULL,
	[status] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 7/6/2024 9:18:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[email] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Appointments] ON 

INSERT [dbo].[Appointments] ([id], [userId], [appointmentDate], [appointmentTime], [purpose], [status]) VALUES (13, 1, CAST(N'2024-07-13' AS Date), CAST(N'00:30:00' AS Time), N'purpose', N'Cancelled')
INSERT [dbo].[Appointments] ([id], [userId], [appointmentDate], [appointmentTime], [purpose], [status]) VALUES (14, 1, CAST(N'2024-07-13' AS Date), CAST(N'11:52:00' AS Time), N'nah', N'Completed')
INSERT [dbo].[Appointments] ([id], [userId], [appointmentDate], [appointmentTime], [purpose], [status]) VALUES (15, 1, CAST(N'2024-07-05' AS Date), CAST(N'03:19:00' AS Time), N'nah', N'Completed')
INSERT [dbo].[Appointments] ([id], [userId], [appointmentDate], [appointmentTime], [purpose], [status]) VALUES (16, 1, CAST(N'2024-07-04' AS Date), CAST(N'01:22:00' AS Time), N'purpose', N'Completed')
INSERT [dbo].[Appointments] ([id], [userId], [appointmentDate], [appointmentTime], [purpose], [status]) VALUES (17, 1, CAST(N'2024-07-27' AS Date), CAST(N'19:29:00' AS Time), N'purpose', N'Completed')
INSERT [dbo].[Appointments] ([id], [userId], [appointmentDate], [appointmentTime], [purpose], [status]) VALUES (18, 1, CAST(N'2024-07-06' AS Date), CAST(N'22:00:00' AS Time), N'purpose', N'Scheduled')
SET IDENTITY_INSERT [dbo].[Appointments] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([id], [username], [password], [email]) VALUES (1, N'hoa', N'$2a$10$HraoyOmFXXtNVJUIB5AwOeX/HZTJcknOU0wFgaUppv84m8QwY62oO', N'hoalqse183138@fpt.edu.vn')
INSERT [dbo].[Users] ([id], [username], [password], [email]) VALUES (5, N'hieu', N'$2a$10$umfF41IU0IJ8.8m3CPvkp.cFr2fpSAmNhop5L7xlS95Ft2zoiq6ni', N'hieutdse183145@fpt.edu.vn')
INSERT [dbo].[Users] ([id], [username], [password], [email]) VALUES (6, N'trang', N'$2a$10$.PNmhOSe.PpKCVsrCeb1l.MDjrPLeGFpAIeofpig2I8ECTItkOO/y', N'thuytrangcancer@gmail.com')
INSERT [dbo].[Users] ([id], [username], [password], [email]) VALUES (7, N'thien', N'$2a$10$KDwwWRo0pC2rVAmIr1djA.exJagVAmZbgHYI2A8zp5b/1QgpPI7i2', N'abc@gmail.com')
INSERT [dbo].[Users] ([id], [username], [password], [email]) VALUES (8, N'a', N'$2a$10$UiUpf5ZZYVxNNfq5SDhdM.kXqeiKu0Zozquu8lvN/haU.hKbnEKgS', N'abc@gmail.com')
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__F3DBC57264735B26]    Script Date: 7/6/2024 9:18:16 PM ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Appointments]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[Users] ([id])
GO
USE [master]
GO
ALTER DATABASE [Workshop2] SET  READ_WRITE 
GO
