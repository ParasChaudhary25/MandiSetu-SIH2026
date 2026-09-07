## 🚀 LIVE DEMO - Team ke liye
Click karo aur bheed dekho: https://mandisetu-xyz.replit.dev/api/crowd/live?mandi=Rampur
Frontend: https://geekyAshuuuu.github.io/MandiSetu-SIH2026/
# MandiSetu-SIH2026
Smart Procurement Assistant for Ministry of Consumer Affairs - Live Queue, QR Scan, DBT Payment Tracking
<img width="1435" height="792" alt="Screenshot 2026-09-05 at 3 57 25 PM" src="https://github.com/user-attachments/assets/aa99200f-6a0d-4140-8936-d47ef129c2fd" />

---
### ✅ My Contribution - LIVE Crowd Intelligence Module
**Member:** [Ashutosh Tiwari]
**Folder:** `/AiService/crowd-api/main.py`
**Live API Link:** https://09afb679-4694-4fd9-8f14-d3a911705a5d-00-1wg4god5xej51.sisko.replit.dev/api/crowd/live?mandi=Rampur
**Demo:**
- Rampur: `/api/crowd/live?mandi=Rampur` -> `{"live_bheed": 43, "avg_wait_min": 86}`
- Ghaziabad: `/api/crowd/live?mandi=Ghaziabad`
**Status:** LIVE and Working on Replit Cloud

## ✅ My Contribution – Smart Procurement & DBT Backend

**Member:** [Paras Chaudhary]  
**Folder:** `/backend/`  
**Technology:** Java, Spring Boot, Supabase PostgreSQL  

### Key Contributions

- Built REST APIs for farmer registration and procurement-centre management  
- Developed live queue token generation and queue-status tracking  
- Implemented QR-based farmer verification at procurement centres  
- Integrated official Government MSP rates for crop procurement  
- Created DBT payment calculation and payment-status tracking  
- Added SMS and IVR notification tracking APIs  

**Complete Flow:** Farmer Registration → Live Queue → QR Verification → MSP Procurement → DBT Payment → SMS / IVR Updates
## ⚙️ Backend Implementation

MandiSetu includes a Java Spring Boot backend connected to Supabase PostgreSQL.

### Backend Modules

- Farmer registration and profile management
- Procurement-centre management
- Live queue token generation and queue-status tracking
- QR-based farmer verification
- Government MSP-based crop procurement
- DBT payment calculation and status tracking
- SMS and IVR notification records

### Technology Stack

| Component | Technology |
|---|---|
| Backend | Java + Spring Boot |
| Database | Supabase PostgreSQL |
| Data Access | Spring Data JPA + Hibernate |
| API Style | REST APIs |
| Rate Source | Government MSP data |

### Complete Procurement Flow

```text
Farmer Registration
→ Procurement Centre Selection
→ Live Queue Token
→ QR Verification
→ Crop Procurement
→ Official MSP Rate Applied
→ DBT Payment Tracking
→ SMS / IVR Notification
```

### Official MSP Integration

MandiSetu uses a verified government MSP rate table instead of allowing manual rate entry. For example, wheat MSP for Rabi Marketing Season 2026–27 is ₹2,585 per quintal (₹25.85 per kg).

Source: [Government of India – Press Information Bureau](https://www.pib.gov.in/PressReleasePage.aspx?PRID=2197694&lang=1&reg=3)

### Run Backend Locally

```bash
cd backend
./mvnw spring-boot:run
```

Backend URL:

```text
http://localhost:8080
```

> Database credentials are kept private in `application-local.properties` and are not uploaded to GitHub.
