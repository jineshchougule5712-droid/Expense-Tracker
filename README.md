# Expense Tracker - Full Stack Application

A modern expense tracking application built with **React** (frontend) and **Spring Boot** (backend) with **MySQL** database.

## Features

✨ **Core Features**
- User authentication (Signup/Login)
- Add, edit, and delete expenses
- Categorize expenses (Food, Transport, Entertainment, etc.)
- View spending analytics with pie charts
- Real-time category breakdowns
- Responsive design for mobile and desktop

📊 **Dashboard Analytics**
- Total spending overview
- Monthly spending tracking
- Average transaction amount
- Visual spending breakdown by category

## Tech Stack

### Frontend (React)
- **Framework**: Next.js 16
- **Language**: TypeScript
- **Styling**: Tailwind CSS
- **Charts**: Recharts
- **State Management**: React Context API
- **Date Handling**: date-fns

### Backend (Spring Boot)
- **Framework**: Spring Boot 3.x
- **Language**: Java 17+
- **Database**: MySQL 5.7+
- **Authentication**: JWT (JSON Web Tokens)
- **ORM**: Spring Data JPA/Hibernate
- **Security**: Spring Security

## Project Structure

```
expense-tracker/
├── app/
│   ├── page.tsx                 # Home (redirects to login/dashboard)
│   ├── login/page.tsx          # Login page
│   ├── signup/page.tsx         # Signup page
│   ├── dashboard/page.tsx      # Main dashboard
│   ├── layout.tsx              # Root layout with providers
│   └── globals.css             # Global styles and theme
├── components/
│   ├── login-form.tsx          # Login form
│   ├── signup-form.tsx         # Signup form
│   ├── expense-form.tsx        # Add/Edit expense modal
│   ├── expense-list.tsx        # List of expenses
│   ├── spending-chart.tsx      # Pie chart visualization
│   └── dashboard-header.tsx    # Dashboard header with stats
├── lib/
│   ├── auth-context.tsx        # Auth state management
│   ├── expense-context.tsx     # Expense state management (with mock data)
│   └── utils.ts                # Utility functions
├── docs/
│   ├── MYSQL_SCHEMA.sql        # Database schema
│   └── SPRING_BOOT_SETUP.md    # Backend setup guide
└── package.json
```

## Getting Started

### Frontend Setup

1. **Install Dependencies**
   ```bash
   pnpm install
   ```

2. **Run Development Server**
   ```bash
   pnpm dev
   ```

3. **Open in Browser**
   - Navigate to `http://localhost:3000`
   - You're redirected to login page
   - Use any email/password to login (mock authentication)

### Frontend Features (Mock Data)

The React frontend currently uses **mock data** and local state management. This allows you to:
- Test the UI/UX without a backend
- Add, edit, and delete expenses in real-time
- See spending analytics and charts
- Explore all dashboard features

**Demo Credentials**: Use any email and password to login

### Backend Setup

Follow the comprehensive guide in `docs/SPRING_BOOT_SETUP.md` to set up:

1. **Create MySQL Database**
   ```bash
   mysql -u root -p < docs/MYSQL_SCHEMA.sql
   ```

2. **Create Spring Boot Project**
   - Use Spring Initializr or Maven archetype
   - Add required dependencies (Web, JPA, Security, MySQL)

3. **Implement Backend Services**
   - User authentication with JWT
   - Expense CRUD operations
   - Database persistence
   - CORS configuration

4. **Run Spring Boot API**
   ```bash
   mvn spring-boot:run
   ```
   - API runs on `http://localhost:8080/api`

### Connecting React to Spring Boot

Once your Spring Boot backend is running:

1. **Update API Base URL**
   ```typescript
   // In lib/expense-context.tsx
   const API_URL = 'http://localhost:8080/api';
   ```

2. **Replace Mock Data with API Calls**
   - Update `addExpense()` to POST to `/api/expenses`
   - Update `useExpenses()` to GET from `/api/expenses`
   - Add JWT token to request headers
   - Handle API errors gracefully

3. **Update Authentication**
   - Replace mock login with API call to `/api/auth/login`
   - Store JWT token in localStorage
   - Include token in all subsequent requests

## API Endpoints (Spring Boot)

### Authentication
```
POST /api/auth/signup
Body: { email, password, name }
Response: { id, email, name, createdAt }

POST /api/auth/login
Body: { email, password }
Response: { token: "JWT_TOKEN" }
```

### Expenses
```
GET /api/expenses
Headers: Authorization: Bearer JWT_TOKEN
Response: [{ id, title, amount, category, date, description }]

POST /api/expenses
Headers: Authorization: Bearer JWT_TOKEN
Body: { title, amount, category, date, description }
Response: { id, title, amount, category, date, description }

PUT /api/expenses/{id}
Headers: Authorization: Bearer JWT_TOKEN
Body: { title, amount, category, date, description }
Response: { id, title, amount, category, date, description }

DELETE /api/expenses/{id}
Headers: Authorization: Bearer JWT_TOKEN
Response: { message: "Expense deleted" }
```

## Database Schema

### Users Table
```sql
id (Primary Key)
email (Unique)
password (Hashed)
name
created_at
updated_at
```

### Expenses Table
```sql
id (Primary Key)
user_id (Foreign Key)
title
amount (Decimal)
category
date
description
created_at
updated_at
```

## Development Guide

### Frontend Development

**Add New Expense Category**
1. Update `CATEGORIES` array in `components/expense-form.tsx`
2. Add color mapping in `components/expense-list.tsx`

**Modify Dashboard Layout**
- Edit `app/dashboard/page.tsx`
- Update grid layout in Tailwind classes

**Customize Colors**
- Theme colors in `app/globals.css`
- CSS variables use OKLch color space

### Backend Development

**Add New Expense Filter**
```java
// In ExpenseRepository.java
List<Expense> findByUserIdAndCategory(Long userId, String category);
```

**Add Statistics Endpoint**
```java
@GetMapping("/statistics")
public ResponseEntity<?> getStatistics() {
    // Return spending by category, monthly trends, etc.
}
```

## Security Notes

### Frontend
- JWT tokens should be stored securely (consider httpOnly cookies)
- Validate user input before submission
- Implement CSRF protection with Spring Security

### Backend
- Hash passwords using BCrypt
- Use HTTPS in production
- Implement rate limiting on auth endpoints
- Validate all input on server side
- Use environment variables for secrets

## Deployment

### Deploy Frontend to Vercel
```bash
npm install -g vercel
vercel
```

### Deploy Backend to Cloud
- **AWS**: EC2 or Elastic Beanstalk
- **Google Cloud**: Cloud Run or Compute Engine
- **DigitalOcean**: App Platform or Droplets
- **Railway.app**: Simple deployment platform

## Future Enhancements

- 📱 Mobile app with React Native
- 📧 Email receipts
- 📤 CSV export
- 🎯 Budget goals and alerts
- 💰 Recurring expenses
- 👥 Shared expenses with friends
- 🏦 Bank integration
- 📈 Advanced analytics and reports

## Troubleshooting

### Frontend Issues
- **Page not loading**: Check `pnpm dev` is running on port 3000
- **Login not working**: Verify AuthProvider is in layout.tsx
- **Chart not showing**: Check expense data has categories

### Backend Issues
- **Database connection error**: Verify MySQL is running and credentials match
- **CORS errors**: Check CORS configuration in Spring Security config
- **JWT token errors**: Verify JWT secret is set in application.properties

## File Structure Details

### Core Components
- `login-form.tsx`: Login authentication form
- `signup-form.tsx`: User registration form
- `expense-form.tsx`: Modal for adding/editing expenses
- `expense-list.tsx`: Table of all expenses with edit/delete
- `spending-chart.tsx`: Pie chart showing category breakdown
- `dashboard-header.tsx`: Top stats cards and add button

### Context Providers
- `auth-context.tsx`: Manages user login state
- `expense-context.tsx`: Manages expenses list and CRUD operations

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is open source and available under the MIT License.

## Support

For issues or questions:
1. Check the documentation in `/docs`
2. Review the code comments
3. Check existing GitHub issues
4. Create a new GitHub issue with details

---

**Happy Tracking! 💰**
