# Functional Requirements

This section describes the key functional requirements of the learning platform. The requirements are organized by the primary user roles: Students and Admin (who also acts as the sole content provider).

---

## Student Functional Requirements

### 1. Homepage and Course Discovery
- **Homepage Browsing for New Visitors**  
  *As an unregistered visitor, I want to explore the homepage so that I can learn about the platform and see featured courses.*
    - **BDD Scenario:**
        - Given I am an unregistered visitor on the platform homepage
        - When I land on the homepage
        - Then I see promotional banners and information about the platform
        - And I see a section of featured courses with brief descriptions and pricing details

- **Course Details and Free Lesson Previews**  
  *As an unregistered visitor, I want to view detailed course information and free lesson previews so that I can evaluate content before registering.*
    - **BDD Scenario:**
        - Given I am an unregistered visitor on the platform
        - When I navigate to the course details page for "Advanced Data Structures"
        - Then I see detailed course information (syllabus, instructor info, pricing)
        - And I see that some lessons are available for free preview
        - When I click on a free preview lesson
        - Then I can view its content
        - And if I try to access locked lessons, I am prompted to register

### 2. Registration, Enrollment, and Payment
- **New Visitor Registration**  
  *As an unregistered visitor, I want to register as a student so that I can enroll in courses and access course content.*
    - **BDD Scenario:**
        - Given I am an unregistered visitor on the platform homepage and browsing the course catalog
        - When I click on the "Register" button
        - And I fill in the registration form (Name, Email, Password)
        - And I submit the registration form
        - Then a new student account is created with the default role "Student"
        - And I am automatically logged in and see a welcome message
        - And I receive in-app and email notifications confirming registration

- **Registration and Enrollment for Course Access**  
  *As a new registered student, I want to enroll in a course (free or paid) so that I can access its content.*
    - **BDD Scenario:**
        - Given I am an unregistered user who is browsing courses
        - When I navigate to the details page of "Introduction to Java" and click "Enroll"
        - Then I am prompted to log in or register
        - When I register and log in, I am redirected to the enrollment process
        - And if the course is free, enrollment is confirmed immediately; if paid, I am redirected to the payment process
        - And I receive in-app and email notifications confirming my enrollment

### 3. Course Interaction and Learning Experience
- **Course Search Functionality**  
  *As a registered student, I want to search for courses by keyword so that I can quickly find those matching my interests.*
    - **BDD Scenario:**
        - Given I am logged in as "Alice Example"
        - When I enter "Java" in the search bar on the Course Catalog page and click "Search"
        - Then I see a list of courses with "Java" in their title or description

- **Enrollment History Access**  
  *As a registered student, I want to view my enrollment history so that I can monitor the courses I am taking and track my progress.*
    - **BDD Scenario:**
        - Given I am logged in as "Alice Example"
        - When I navigate to my "Dashboard" or "My Enrollments" page
        - Then I see a list of courses I am enrolled in with details (title, enrollment date, progress status)

- **Personalized Course Recommendations**  
  *As a registered student, I want to receive course recommendations tailored to my interests so that I can discover new relevant courses.*
    - **BDD Scenario:**
        - Given I am logged in as "Alice Example" and have enrolled in or browsed courses
        - When I visit my dashboard
        - Then I see personalized recommendations with course titles, brief descriptions, and pricing information

- **Profile Update**  
  *As a registered student, I want to update my personal profile information so that my account details remain accurate.*
    - **BDD Scenario:**
        - Given I am logged in as "Alice Example" and on my Profile page
        - When I change my name and email, then click "Save Changes"
        - Then my profile is updated and I receive in-app and email notifications confirming the update

- **Course Progress Tracking**  
  *As a student enrolled in a course, I want to mark lessons as completed so that I can track my progress and resume where I left off.*
    - **BDD Scenario:**
        - Given I am enrolled in "Introduction to Java" and viewing the lesson "Variables and Data Types"
        - When I click "Mark as Completed"
        - Then my progress is updated, and I see a confirmation message
        - And if I hit a milestone, I receive a congratulatory notification

- **Free Lesson Preview and Full Course Enrollment**  
  *As a student, I want to preview free lessons in a paid course so that I can evaluate its content before purchasing full access.*
    - **BDD Scenario:**
        - Given I navigate to the details page of a paid course with some free lessons
        - When I click the "Free Lessons" tab, I can view free lessons
        - When I try to access a locked lesson, I am prompted to "Enroll Now"
        - Then, after payment, my enrollment is confirmed and I receive notifications confirming full access

- **Course Discussion Participation**  
  *As a student, I want to participate in course discussions so that I can ask questions and share insights with peers.*
    - **BDD Scenario:**
        - Given I am enrolled in "Advanced Data Structures" and navigate to its Discussion Forum
        - When I create a new post with a question and submit it
        - Then my post is published and I receive a notification confirming the publication

- **Course Quiz/Assessment**  
  *As a student, I want to take quizzes within a course so that I can evaluate my understanding and get immediate feedback.*
    - **BDD Scenario:**
        - Given I am enrolled in "Introduction to Data Science"
        - When I complete and submit a quiz
        - Then I see my score and receive feedback along with a confirmation notification

- **Viewing Course Completion Certificate**  
  *As a student, I want to view and download my certificate after completing a course so that I can showcase my achievement.*
    - **BDD Scenario:**
        - Given I have completed "Advanced Data Structures"
        - When I navigate to the "My Certificates" page and click on a certificate
        - Then the certificate is displayed with my name, course title, and completion date
        - And I receive an in-app notification confirming availability

- **Leaving a Course Review**  
  *As a student, I want to submit a review for a completed course so that I can provide feedback and help others make informed decisions.*
    - **BDD Scenario:**
        - Given I have completed "Introduction to Java"
        - When I navigate to the "Review" page, enter a rating and comment, and submit
        - Then my review is saved, and I receive notifications confirming submission

- **Referral to the Platform**  
  *As a student, I want to refer friends to the platform so that I can help others discover great courses and earn rewards.*
    - **BDD Scenario:**
        - Given I am on the "Referral Program" page
        - When I copy and share my referral code/link
        - Then the referral is recorded, and I receive notifications when my friend registers using my code

- **Technical Support Request**  
  *As a student, I want to submit a support ticket when I encounter issues so that I can get help quickly.*
    - **BDD Scenario:**
        - Given I experience a technical issue
        - When I navigate to "Help & Support," fill in and submit a support form
        - Then a support ticket is created, and I receive notifications confirming submission

- **Newsletter Subscription**  
  *As a student, I want to subscribe to the newsletter so that I receive updates, tips, and promotions via email.*
    - **BDD Scenario:**
        - Given I am on the "Newsletter Subscription" section of my account settings
        - When I check the subscription box and save preferences
        - Then my subscription is confirmed, and I receive a welcome email

---

# Admin Functional Requirements

## 1. Course Management

- **List Courses**  
  *As an admin, I want to list all courses so that I can review and manage the available course offerings.*
    - **BDD Scenario:**
        - Given I am logged in as an admin
        - When I navigate to the "Manage Courses" section and click "List Courses"
        - Then I see a list of all courses with details such as title, description, category, and status

- **Create Course**  
  *As an admin, I want to create a new course so that I can add fresh content to the platform.*
    - **BDD Scenario:**
        - Given I am logged in as an admin
        - When I navigate to the "Create Course" page
        - And I fill in the course details:
            - Title: "New Course Title"
            - Description: "Detailed description of the course"
            - Category: "Computer Science"
            - Free: false
        - And I submit the form
        - Then a new course is created with a unique course ID
        - And I see a confirmation message "Course created successfully."

- **Update Course**  
  *As an admin, I want to update an existing course so that the course information remains current.*
    - **BDD Scenario:**
        - Given I am logged in as an admin and a course with ID 458 exists
        - When I navigate to the "Edit Course" page for course 458
        - And I update the course details (e.g., change the title to "Updated Course Title", update the description, set status to "Published")
        - And I submit the changes
        - Then the course with ID 458 is updated accordingly
        - And I see a confirmation message "Course updated successfully."

- **Delete Course**  
  *As an admin, I want to delete a course so that outdated or irrelevant courses are removed from the platform.*
    - **BDD Scenario:**
        - Given I am logged in as an admin and a course with ID 458 exists
        - When I navigate to the "Manage Courses" section and click the "Delete" button for course 458
        - And I confirm the deletion action
        - Then the course with ID 458 is removed from the platform
        - And I see a confirmation message "Course with ID 458 deleted successfully."

## 2. User Management

- **List Users**  
  *As an admin, I want to view all users so that I can manage user accounts and monitor platform activity.*
    - **BDD Scenario:**
        - Given I am logged in as an admin
        - When I navigate to the "User Management" section and click "List Users"
        - Then I see a list of users with details such as user IDs, names, emails, and roles

- **Update User Status**  
  *As an admin, I want to update a user's status so that I can enforce platform policies (e.g., suspend a problematic user).*
    - **BDD Scenario:**
        - Given I am logged in as an admin and a user with ID 123 exists with status "Active"
        - When I navigate to the "User Management" section
        - And I select the user with ID 123
        - And I update their status to "Suspended"
        - And I submit the update
        - Then the user's status is updated to "Suspended"
        - And I see a confirmation message "User status updated successfully."

## 3. Support Management

- **List Support Tickets**  
  *As an admin, I want to view all support tickets so that I can address user issues promptly.*
    - **BDD Scenario:**
        - Given I am logged in as an admin
        - When I navigate to the "Support Tickets" section and click "List Support Tickets"
        - Then I see a list of support tickets with details such as ticket IDs, student IDs, issue types, messages, and statuses

- **Update Support Ticket Status**  
  *As an admin, I want to update the status of a support ticket so that I can mark issues as resolved and communicate responses to users.*
    - **BDD Scenario:**
        - Given I am logged in as an admin and a support ticket with ID 101 exists with status "Open"
        - When I navigate to the support ticket details for ticket 101
        - And I update the ticket with a response "The issue has been fixed. Please try again."
        - And I change the status to "Resolved"
        - And I submit the update
        - Then the support ticket with ID 101 is updated with status "Resolved"
        - And I see a confirmation message "Support ticket updated successfully."

## 4. Tax Policy Management

- **Create Tax Policy**  
  *As an admin, I want to create new tax policies so that correct tax rules are applied globally.*
    - **BDD Scenario:**
        - Given I am logged in as an admin
        - When I navigate to the "Tax Policies" section and click "Create New Tax Policy"
        - And I fill in the tax policy details:
            - Country Code: "GB"
            - Tax Name: "VAT"
            - Tax Rate: 0.20
            - Effective From: "2023-01-01"
        - And I submit the form
        - Then a new tax policy is created with a unique tax policy ID
        - And I see a confirmation message "Tax policy created successfully."

- **Update Tax Policy**  
  *As an admin, I want to update existing tax policies so that tax rules remain accurate.*
    - **BDD Scenario:**
        - Given I am logged in as an admin and a tax policy with ID 3 exists
        - When I navigate to the "Tax Policies" section and select the tax policy with ID 3
        - And I update the tax rate to 0.18 and set Effective To to "2024-12-31"
        - And I submit the update
        - Then the tax policy is updated accordingly
        - And I see a confirmation message "Tax policy updated successfully."

- **Delete Tax Policy**  
  *As an admin, I want to delete a tax policy so that outdated rules are removed from the system.*
    - **BDD Scenario:**
        - Given I am logged in as an admin and a tax policy with ID 3 exists
        - When I navigate to the "Tax Policies" section and click the "Delete" button for tax policy 3
        - And I confirm the deletion
        - Then the tax policy with ID 3 is removed from the system
        - And I see a confirmation message "Tax policy deleted successfully."

## 5. Platform Announcements

- **Post an Announcement**  
  *As an admin, I want to send announcements to all users so that I can communicate important updates or promotions.*
    - **BDD Scenario:**
        - Given I am logged in as an admin
        - When I navigate to the "Announcements" section and click "Create Announcement"
        - And I fill in the announcement details:
            - Title: "Scheduled Maintenance"
            - Message: "The platform will be down for maintenance on 2024-05-01 from 02:00 to 04:00."
        - And I submit the announcement
        - Then the announcement is posted to all users
        - And I see a confirmation message "Announcement posted successfully."

## 6. Detailed User Profiles and Activity Logs

- **View Detailed User Profile**  
  *As an admin, I want to view detailed user profiles including activity logs so that I can monitor user behavior and troubleshoot issues.*
    - **BDD Scenario:**
        - Given I am logged in as an admin
        - When I navigate to the "User Management" section and select a user with ID 123
        - Then I see detailed profile information including login history, enrollment records, and support ticket activity

---

## Notifications in Play (For Both Students and Admin)
- **In-App Notifications:**  
  Delivered immediately in the UI to confirm actions (registration, enrollment, profile updates, etc.).

- **Email Notifications:**  
  Sent asynchronously to provide formal confirmations and additional details (registration confirmations, enrollment, course completion certificates, etc.).

- **Conditional Notifications:**  
  Such as progress milestone notifications, referral rewards, or support ticket updates.