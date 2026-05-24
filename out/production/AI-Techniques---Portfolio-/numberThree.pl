% -----------------------------
% MAIN SOLUTION
% -----------------------------

medieval_literature_office(Office) :-
    solution(Offices),
    member(Office, Offices),
    Office = office(_, _, _, medieval_literature, _, _, _).

solution(Offices) :-

    Offices = [
        office(1, D1, C1, R1, U1, Col1, Dr1),
        office(2, D2, C2, R2, U2, Col2, Dr2),
        office(3, D3, C3, R3, U3, Col3, Dr3),
        office(4, D4, C4, R4, U4, Col4, Dr4),
        office(5, D5, C5, R5, U5, Col5, Dr5)
    ],

    Departments = [computer_science, history, philosophy, mathematics, physics],
    Cars        = [audi, tesla, volvo, bmw, mercedes],
    Research    = [artificial_intelligence, neuroscience, climate_change, quantum_physics, medieval_literature],
    Universities= [cambridge, oxford, mit, harvard, stanford],
    Colors      = [blue, red, white, green, yellow],
    Drinks      = [espresso, black_coffee, herbal_tea, green_tea, teaa],

    all_different(Departments),
    all_different(Cars),
    all_different(Research),
    all_different(Universities),
    all_different(Colors),
    all_different(Drinks),

    % departments(Departments),
    % cars(Cars),
    % research(Research),
    % universities(Universities),
    % colors(Colors),
    % drinks(Drinks),

% -----------------------------
% GIVEN FIXED FACTS
% -----------------------------

    member(office(1, _, _, _, cambridge, _, _), Offices),
    member(office(3, _, _, _, _, _, green_tea), Offices),

% -----------------------------
% SUBJECT CONSTRAINTS
% -----------------------------

    member(office(_, computer_science, _, _, _, blue, _), Offices),
    member(office(_, mathematics, _, _, _, red, _), Offices),
    member(office(_, philosophy, volvo, _, _, _, _), Offices),
    member(office(_, history, _, _, _, _, black_coffee), Offices),

% -----------------------------
% RESEARCH CONSTRAINTS
% -----------------------------

    member(office(_, _, _, artificial_intelligence, _, _, espresso), Offices),
    member(office(_, _, _, climate_change, _, _, herbal_tea), Offices),
    member(office(_, _, mercedes, quantum_physics, _, _, _), Offices),
    member(office(_, _, _, neuroscience, _, _, _), Offices),

% -----------------------------
% UNIVERSITY + CAR CONSTRAINTS
% -----------------------------

    member(office(_, _, tesla, _, oxford, _, _), Offices),
    member(office(_, _, _, _, mit, white, _), Offices),

    % permutation(Departments, [D1,D2,D3,D4,D5]),
    % permutation(Cars, [C1,C2,C3,C4,C5]),
    % permutation(Research, [R1,R2,R3,R4,R5]),
    % permutation(Universities, [U1,U2,U3,U4,U5]),
    % permutation(Colors, [Col1,Col2,Col3,Col4,Col5]),
    % permutation(Drinks, [Dr1,Dr2,Dr3,Dr4,Dr5]),

% Stanford is right of Harvard
    right_of(
        office(_, _, _, _, stanford, _, _),
        office(_, _, _, _, harvard, _, _),
        Offices
    ),

% -----------------------------
% ADJACENCY CONSTRAINTS
% -----------------------------

    adjacent(
        office(_, _, bmw, _, _, _, _),
        office(_, _, _, _, _, green, _),
        Offices
    ),

    adjacent(
        office(_, _, _, neuroscience, _, _, _),
        office(_, _, audi, _, _, _, _),
        Offices
    ),

    adjacent(
        office(_, _, _, _, cambridge, _, _),
        office(_, _, _, _, _, yellow, _),
        Offices
    ),

% -----------------------------
% CAR/OTHER LINKS
% -----------------------------

    member(office(_, _, volvo, philosophy, _, _, _), Offices).



% -----------------------------
% HELPERS
% -----------------------------

adjacent(A, B, Offices) :-
    nextto(A, B, Offices).

adjacent(A, B, Offices) :-
    nextto(B, A, Offices).

right_of(A, B, Offices) :-
    append(_, [B|Rest], Offices),
    member(A, Rest).

all_different([]).
all_different([H|T]) :-
    \+ member(H, T),
    all_different(T).


% Solution
%office(1, computer_science, audi, artificial_intelligence, cambridge, blue, espresso), 
%office(2, history, tesla, neuroscience, oxford, yellow, black_coffee), 
%office(3, philosophy, volvo, philosophy, mit, white, green_tea), 
%office(4, mathematics, bmw, climate_change, harvard, red, herbal_tea),
%office(5, _, mercedes, quantum_physics, stanford, green, _)] .