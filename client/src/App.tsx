import './App.css';
import { Terminal } from "lucide-react";

import {
  Alert,
  AlertDescription,
  AlertTitle,
} from "@/components/ui/alert";

function App() {

  return (
    <>
      <Alert>
        <Terminal className="h-4 w-4" />
        <AlertTitle>Hello, World!</AlertTitle>
        <AlertDescription>
          Restaura coming soon...
        </AlertDescription>
      </Alert>
    </>
  )
}

export default App
