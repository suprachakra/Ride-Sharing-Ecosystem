// Report for ride ratings, feedback, and sentiment analysis
*Description:*  
Focuses on customer feedback and sentiment, including average ride ratings and complaint analysis.

**Report Contents Specification:**

- **KPIs & Measures:**  
  - **Average Ride Rating (Rider & Driver):**  
    ```DAX
    AvgRideRating = AVERAGE('Driver_Ratings'[rating])
    ```
  - **Complaint Count by Category:** (Assumes a table or categorization exists)  
  - **Sentiment Score:** Use Power BI’s AI Insights or a custom DAX measure for textual analysis.
  
- **Visuals:**  
  - Scorecards for average ratings  
  - Bar/Column charts for complaint frequencies  
  - Word cloud or sentiment visual for feedback analysis

*Implementation:*  
Set up the data model and visuals in Power BI Desktop; then save as `customer_experience.pbix`.

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#f4f4f4'}}}%%
flowchart TD
    subgraph Data_Sources
        A[Booking Feedback]
        B[Driver Ratings]
        C[Complaints]
    end

    A --> ETL
    B --> ETL
    C --> ETL

    subgraph ETL_Process
        ETL[[ETL Pipeline]] -->|Cleaned Data| DW[(Data Warehouse)]
    end

    subgraph DAX_Calculations
        DW --> AR["Avg Ride Rating: AVERAGE(Driver_Ratings[rating])"]
        DW --> CC["Complaint Count: COUNTROWS(Complaints)"]
        DW --> SS["Sentiment Score: AI-Based or Custom Measure"]
    end

    subgraph Visualizations
        AR --> V1[Scorecards - Average Ratings]
        CC --> V2[Bar/Column Charts - Complaint Frequencies]
        SS --> V3[Word Cloud / Sentiment Analysis]
    end

    subgraph Output
        V1 --> DASHBOARD[[Customer Experience Dashboard]]
        V2 --> DASHBOARD
        V3 --> DASHBOARD
    end

    classDef source fill:#c8e6c9,stroke:#2e7d32
    classDef etl fill:#bbdefb,stroke:#1976d2
    classDef calc fill:#fff9c4,stroke:#f9a825
    classDef viz fill:#f8bbd0,stroke:#d81b60
    classDef output fill:#e1bee7,stroke:#8e24aa

    class A,B,C source
    class ETL etl
    class DW,AR,CC,SS calc
    class V1,V2,V3 viz
    class DASHBOARD output
```
