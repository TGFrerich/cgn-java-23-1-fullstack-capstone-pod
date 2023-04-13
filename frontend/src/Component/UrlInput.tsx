import React, {ChangeEvent, FormEvent} from 'react';

type UrlInputProps = {
    value: string;
    onChange: (event: ChangeEvent<HTMLInputElement>) => void;
    onSubmit: (event: FormEvent<HTMLFormElement>) => void;
};

function UrlInput({value, onChange, onSubmit}: UrlInputProps) {
    return (
        <div>
            <form onSubmit={onSubmit}>
                <label>
                    Podcast:
                    <input type="text" value={value} onChange={onChange}/>
                </label>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

export default UrlInput;
