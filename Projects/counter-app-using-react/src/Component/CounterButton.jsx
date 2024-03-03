
export default function CounterButton(props) {
    return (
        <div>
            <button onClick={() => props.incrementCount(props.incrementBy)}>+{props.incrementBy}</button>
            <button onClick={() => props.decrementCount(props.incrementBy)}>-{props.incrementBy}</button>
        </div>
    );
}