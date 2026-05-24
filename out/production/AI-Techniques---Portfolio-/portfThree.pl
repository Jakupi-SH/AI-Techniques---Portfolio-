%office(Number, Department, Car, Research, University, Decor, Drink)

Offices = [
office(1, _, _, _, cambridge, _, _),
office(2, _, _, _, _, _, _),
office(3, _, _, _, _, _, green_tea),
office(4, _, _, _, _, _, _),
office(5, _, _, _, _, _, _)
]

adjacent(A, B, Offices) :-
    nextto(A, B, Offices).

adjacent(A, B, Offices) :-
    nextto(B, A, Offices).

right_of(A, B, Offices) :-
    append(_, [B|Rest], Offices),
    member(A, Rest).


office(_, computer_science, _, _, _, blue, _).

% 2. The professor who graduated from Oxford drives a Tesla.
office(_, _, tesla, _, oxford, _, _).

% 3. The professor researching Artificial Intelligence drinks espresso.
office(_, _, _, artificial_intelligence, _, _, espresso).

% 4. The professor in office #1 graduated from Cambridge.
office(1, _, _, _, cambridge, _, _).

% 5. The professor who drives a BMW's office is adjacent to the office with green decor.
adjacent(
    office(_, _, bmw, _, _, _, _),
    office(_, _, _, _, _, green, _)
).

% 6. The professor researching Climate Change drinks herbal tea.
office(_, _, _, climate_change, _, _, herbal_tea).

% 7. The professor who teaches Mathematics has red office decor.
office(_, mathematics, _, _, _, red, _).

% 8. The professor who drives a Mercedes researches Quantum Physics.
office(_, _, mercedes, quantum_physics, _, _, _).

% 9. The professor in the middle office (office #3) drinks green tea.
office(3, _, _, _, _, _, green_tea).

% 10. The professor from Cambridge's office is next to the office with yellow decor.
adjacent(
    office(_, _, _, _, cambridge, _, _),
    office(_, _, _, _, _, yellow, _)
).

% 11. The professor who drives a Volvo teaches Philosophy.
office(_, philosophy, volvo, _, _, _, _).

% 12. The professor researching Neuroscience's office is adjacent
%     to the office of the professor who drives an Audi.
adjacent(
    office(_, _, _, neuroscience, _, _, _),
    office(_, _, audi, _, _, _, _)
).

% 13. The professor who teaches History drinks black coffee.
office(_, history, _, _, _, _, black_coffee).

% 14. The professor with white office decor graduated from MIT.
office(_, _, _, _, mit, white, _).

% 15. The professor who graduated from Stanford's office is
%     on the right of the professor from Harvard.
right_of(
    office(_, _, _, _, stanford, _, _),
    office(_, _, _, _, harvard, _, _)
).

