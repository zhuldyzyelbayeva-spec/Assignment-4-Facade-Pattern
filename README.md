# Online Order Processing — Facade Pattern

Java 17 example for Assignment #4 (Option B).

## What it does
`Main` calls `OrderFacade.placeOrder(cart, customer)`. The facade reserves stock, charges payment, schedules shipping and sends confirmation. On failure, it attempts rollback.

## Run (from project folder)
```bash
mkdir -p out
javac -d out src/main/java/com/example/orders/*.java src/test/java/com/example/orders/*.java
java -cp out com.example.orders.Main
java -cp out com.example.orders.FacadeTest
```

## Files
- `src/main/java/...`: Java program
- `src/test/java/...`: test scenarios
- `docs/uml.puml`: editable UML source
- `docs/uml_preview.png`: UML image
- `Facade_Report.docx`: example report with UML and explanations

## Note
This is a teaching example: payment and shipping are simulated. Rollback is best-effort and there is no database. Add your name, group and GitHub URL to the report after publishing.
