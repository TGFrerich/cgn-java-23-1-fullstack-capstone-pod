import React, {ChangeEvent, FormEvent} from 'react';

type UrlInputProps = {
    value: string;
    onChange: (event: ChangeEvent<HTMLInputElement>) => void;
    onSubmit: (event: FormEvent<HTMLFormElement>) => void;
};

function UrlInput({value, onChange, onSubmit}: UrlInputProps) {
    return (
        <div className="podcast-form">
            <form onSubmit={onSubmit}>
                <label>
                    <input type="text" value={value} onChange={onChange}
                           placeholder="Paste your url to your podcast audio file"/>
                </label>
                <button className="button-submit" type="submit">Submit</button>
            </form>
        </div>
    );
}

export default UrlInput;
