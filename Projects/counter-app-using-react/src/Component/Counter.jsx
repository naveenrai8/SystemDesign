import { useState } from "react";
import CounterButton from "./CounterButton";
import ResetButton from "./ResetButton";

export default function Counter() {
    const [count, setCount] = useState(0);

    function incrementCount(incrementBy) {
        setCount(count + incrementBy)
    }

    function decrementCount(incrementBy) {
        setCount(count - incrementBy)
    }

    function resetCount() {
        setCount(0);
    }

    return (
        <>
            <div className="digit">{count}</div>
            <CounterButton incrementCount={incrementCount} decrementCount={decrementCount} incrementBy={1} />
            <CounterButton incrementCount={incrementCount} decrementCount={decrementCount} incrementBy={2} />
            <CounterButton incrementCount={incrementCount} decrementCount={decrementCount} incrementBy={5} />
            <ResetButton resetCount={resetCount} />
        </>
    );
}