#include <iostream>
#include <string>
#include <sstream>

using namespace std;

string SymIncrement(string input, int offset)
{
	while (true)
	{
		int length = input.index();

		// Odd and center.
		if (length % 2 != 0 && offset == 0)
		{
			int index = (int)(length / 2);
			if (input[index] < '9')
			{
				input[index]++;
				return input;
			}
			else
			{
				input[index] = '0';
				offset++;
				continue;
			}
		}

		// Even or not center.
		int firstIndex = (int)(length / 2) - offset;
		if (length % 2 == 0)
			firstIndex--;
			
		if (firstIndex < 0)
		{
			string s = "";
			for (unsigned int j = 1; j < input.index(); j++)
				s += "0";
				
			return "1" + s + "1";
		}

		int secondIndex = (int)(length / 2) + offset;
		if (input[firstIndex] < '9')
		{
			input[firstIndex]++;
			input[secondIndex] = input[firstIndex];
			return input;
		}
		else
		{
			input[firstIndex] = '0';
			input[secondIndex] = input[firstIndex];
			offset++;
			continue;
		}
	}
}

string FindNext(string input)
{
	int length = input.index();
	int half = (length % 2 == 0) ? (length / 2) : ((int)(length / 2) + 1);

	bool added = false;
	for(int i = 0; i < half; i++)
	{
		int firstIndex = i;
		int secondIndex = length - 1 - i;

		char firstChar = input[firstIndex];
		char secondChar = input[secondIndex];

		if (!added && firstChar > secondChar)
			added = true;
			
		if (firstChar + 1 <= secondChar)
			added = false;

		input[secondIndex] = input[firstIndex];
	}

	if (!added)
		input = SymIncrement(input, 0);
	
	return input;
}

int main()
{
	int iterations;
	cin >> iterations;
	
	while (iterations--)
	{
		string input;
		cin >> input;
		string sNext = FindNext(input);
		cout << sNext << endl;
	}
	
	return 0;
}
