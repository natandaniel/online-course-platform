# REST API Overview

## 1. Authentication & User Management

### **1.1 Authentication**
- `POST /api/v1/auth/register` → Register a new user (student or admin).
- `POST /api/v1/auth/login` → Authenticate user and return JWT.
- `POST /api/v1/auth/logout` → Invalidate JWT token.
- `POST /api/v1/auth/refresh-token` → Refresh access token.

### **1.2 User Management**
- `GET /api/v1/users/me` → Get the authenticated user's details.
- `PUT /api/v1/users/me` → Update profile details.
- `GET /api/v1/users/{id}` → Get details of a specific user (**Admin only**).
- `GET /api/v1/users` → List all users (**Admin only**).
- `PATCH /api/v1/users/{id}/status` → Update user status (e.g., suspend, activate) (**Admin only**).

---

## 2. Course Management (Courses → Modules → Lessons)

### **2.1 Course Catalog**
- `GET /api/v1/courses` → Retrieve list of all available courses.
- `GET /api/v1/courses/{courseId}` → Get details of a specific course.

### **2.2 Course Modules**
- `GET /api/v1/courses/{courseId}/modules` → Retrieve all modules of a course.
- `GET /api/v1/courses/{courseId}/modules/{moduleId}` → Get details of a specific module.

### **2.3 Lessons within Modules**
- `GET /api/v1/courses/{courseId}/modules/{moduleId}/lessons` → List lessons within a module.
- `GET /api/v1/courses/{courseId}/modules/{moduleId}/lessons/{lessonId}` → Retrieve specific lesson content.

### **2.4 Admin Course Management**
- `POST /api/v1/courses` → Create a new course (**Admin only**).
- `PUT /api/v1/courses/{courseId}` → Update course details (**Admin only**).
- `DELETE /api/v1/courses/{courseId}` → Delete a course (**Admin only**).
- `POST /api/v1/courses/{courseId}/modules` → Add a module to a course (**Admin only**).
- `PUT /api/v1/courses/{courseId}/modules/{moduleId}` → Update module details (**Admin only**).
- `DELETE /api/v1/courses/{courseId}/modules/{moduleId}` → Delete a module (**Admin only**).
- `POST /api/v1/courses/{courseId}/modules/{moduleId}/lessons` → Add a lesson to a module (**Admin only**).
- `PUT /api/v1/courses/{courseId}/modules/{moduleId}/lessons/{lessonId}` → Update lesson content (**Admin only**).
- `DELETE /api/v1/courses/{courseId}/modules/{moduleId}/lessons/{lessonId}` → Delete a lesson (**Admin only**).

---

## 3. Enrollment & Course Access

### **3.1 Student Enrollment**
- `POST /api/v1/enrollments` → Enroll in a course (requires authentication).
- `GET /api/v1/enrollments` → Get list of enrolled courses.
- `GET /api/v1/enrollments/{enrollmentId}` → Get details of a specific enrollment.

### **3.2 Course Progress Tracking**
- `GET /api/v1/progress` → Get progress across all enrolled courses.
- `POST /api/v1/progress/{lessonId}/complete` → Mark a lesson as completed.

---

## 4. Payments & Subscriptions

### **4.1 Course Payments**
- `POST /api/v1/payments/intent` → Create a payment intent (Stripe integration).
- `POST /api/v1/payments/confirm` → Confirm a payment transaction.
- `GET /api/v1/payments/history` → Retrieve payment history.

---

## 5. Reviews & Community Engagement

### **5.1 Course Reviews**
- `POST /api/v1/reviews` → Submit a course review.
- `GET /api/v1/reviews/{courseId}` → Get all reviews for a course.

### **5.2 Discussion Forums (Optional)**
- `POST /api/v1/discussions/{courseId}` → Create a discussion post.
- `GET /api/v1/discussions/{courseId}` → Get discussion threads for a course.

---

## 6. Notifications & Support

### **6.1 Notifications**
- `GET /api/v1/notifications` → Retrieve user notifications.
- `PATCH /api/v1/notifications/{id}/read` → Mark a notification as read.

### **6.2 Support & Ticketing**
- `POST /api/v1/support/tickets` → Submit a new support ticket.
- `GET /api/v1/support/tickets` → Get list of submitted tickets.
- `PATCH /api/v1/support/tickets/{id}` → Update ticket status (**Admin only**).

---

## 7. Admin Features

### **7.1 Admin Dashboard & Reporting**
- `GET /api/v1/admin/analytics` → Get system-wide analytics and insights (**Admin only**).
- `GET /api/v1/admin/transactions` → View all payment transactions (**Admin only**).

### **7.2 Platform Configuration**
- `POST /api/v1/admin/announcements` → Publish an announcement (**Admin only**).
- `POST /api/v1/admin/tax-policies` → Create a tax policy (**Admin only**).
- `PATCH /api/v1/admin/tax-policies/{id}` → Update tax policy (**Admin only**).

---

# API Design Considerations

### **1. Versioning**
- All API endpoints are prefixed with `/api/v1/`, allowing future upgrades (`v2`, `v3`, etc.).

### **2. Authentication**
- JWT-based authentication for all protected endpoints.
- Admin-specific routes require **Admin Role Authorization**.

### **3. Pagination & Filtering**
- `GET /api/v1/courses?page=1&size=10` → Example of paginated responses.
- Sorting and filtering supported using query parameters (e.g., `?sort=rating`).

### **4. Error Handling**
- API returns standard HTTP status codes (`400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`).
- Standardized error responses:
  ```json
  {
    "timestamp": "2024-02-20T10:30:00Z",
    "status": 400,
    "error": "Bad Request",
    "message": "Invalid course ID"
  }