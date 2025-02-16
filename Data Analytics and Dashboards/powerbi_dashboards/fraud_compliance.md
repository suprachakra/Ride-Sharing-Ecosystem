// Report for fraud detection, compliance violations, and safety issues
*Description:*  
Monitors fraud detection, compliance violations, and safety issues.

**Report Contents Specification:**

- **KPIs & Measures:**  
  - **Fraudulent Transaction Rate:**  
    ```DAX
    FraudRate = DIVIDE(CALCULATE(COUNTROWS('Payments'), 'Payments'[status] = "FAILED"), COUNTROWS('Payments'))
    ```
  - **Compliance Violation Count:** Count rows in COMPLIANCE_LOGS.
  - **Safety Complaints:** Aggregated from feedback logs.
  
- **Visuals:**  
  - Red-flag KPI cards for fraud and compliance  
  - Heatmap showing geographic compliance issues  
  - Trend lines for regulatory violations over time

*Implementation:*  
Build these visuals in Power BI Desktop and save as `fraud_compliance.pbix`.

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#f4f4f4'}}}%%
flowchart TD
    subgraph Data_Sources
        A[Payments]
        B[Compliance Logs]
        C[Safety Complaints]
    end

    A --> ETL
    B --> ETL
    C --> ETL

    subgraph ETL_Process
        ETL[[ETL Pipeline]] -->|Cleaned Data| DW[(Data Warehouse)]
    end

    subgraph DAX_Calculations
        DW --> FR["Fraud Rate: DIVIDE(COUNTROWS(Filter(Payments,status='FAILED')), COUNTROWS(Payments))"]
        DW --> CV["Compliance Violations: COUNTROWS(Compliance Logs)"]
        DW --> SC["Safety Complaints: Aggregated Count"]
    end

    subgraph Visualizations
        FR --> V1[KPI Cards -Fraud Rate]
        CV --> V1
        SC --> V2[Trend Lines - Safety Complaints]
        DW --> V3[Geo-Heatmap - Compliance Issues]
    end

    subgraph Output
        V1 --> DASHBOARD[[Fraud & Compliance Dashboard]]
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
    class DW,FR,CV,SC calc
    class V1,V2,V3 viz
    class DASHBOARD output

```
