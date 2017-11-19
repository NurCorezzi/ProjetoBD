#include <string>
#include <fstream>
#include <iostream>
#include <new>
#include <map>
#include <vector>
#include <algorithm>

void *__gxx_personality_v0;
using namespace std;

const int INST_COUNT = 2367;

string aluno_src = "AM_ALUNOS.CSV";
string ies_src = "DM_IES.CSV";
string local_src = "DM_LOCAL_OFERTA.CSV";
string docente_src = "DM_DOCENTE.CSV";
string curso_src = "DM_CURSO.CSV";

string aluno_out = "DM_ALUNO_FINAL.csv";
string ies_out = "DM_IES_FINAL.csv";
string local_out = "DM_LOCAL_OFERTA_FINAL.csv";
string docente_out = "DM_DOCENTE_FINAL.csv";
string curso_out = "DM_CURSO_FINAL.csv";

/*
 *Colunas a serem filtradas
 */
vector<int> aluno_colunms_vector({1,7,26,27,28,30,32,33,34,35,
								  36,38,43,44,45,51,52,57,59,
								  60,61,72,75,81,100,103,113,114});
vector<int> ies_colunms_vector({1,2,3,5,7,9,11,13,16,21,22,23,24,27,28,
								29,30,31,34,37,38,39,40,41,42});
vector<int> local_colunms_vector({3,5,7,8,9,10,11,12,13,14,15,16});
vector<int> docente_colunms_vector({1,8,9,11,13,15,17,18,19,20,21,23,
									26,27,28,29,41,42,45,46,47,});
vector<int> curso_colunms_vector({1,8,10,11,13,14,16,26,28,30,31,33,
								  34,35,37,38,49,50,51,52,53,54,77,
								  78,79,92,93,94,95});

const int ALUNO_COLUNMS_COUNT = 117;
const int IES_COLUNMS_COUNT = 43;
const int LOCAL_COLUNMS_COUNT = 16;
const int DOCENTE_COLUNMS_COUNT = 50;
const int CURSO_COLUNMS_COUNT = 95;
						   
map<int,int> aluno_colunms_filter;
map<int,int> ies_colunms_filter;
map<int,int> local_colunms_filter;
map<int,int> docente_colunms_filter;
map<int,int> curso_colunms_filter;

string getRow(ifstream &file, map<int,int> &colunms_filter, const int cols_count)
{
	string input;
	string toFile = "";
	int i;
	for(int i = 1; i< cols_count - 1;i++)
	{
		getline(file, input, '|');
		if(colunms_filter[i])
			toFile += input + "|";
	}
	getline(file, input);
	if(colunms_filter[i])
		toFile += input + '\n';
	else
		toFile[toFile.size()-1] = '\n';
	
	//cout << toFile ;
	return toFile;
}

void fillFilter(vector<int> &colunms_vector, map<int,int> &colunms_filter)
{
	for(int i = 0; i < colunms_vector.size(); i++)
		colunms_filter[colunms_vector[i]-1] = 1; 
}

map<string,int> getIesMatches()
{
	ifstream file;
	file.open(ies_src);
	map<string,int> matches;
	string input;
	
	cout << "IES: ";
	getline(file, input);
	for(int i = 0; i < INST_COUNT; i++)
	{
		getline(file, input, '|');
		matches[input] = 1;
		cout << input << ", ";
		getline(file, input);
	}
	cout << endl;
	
	file.close();
	
	return matches;
}

void buildFile(map<string,int> &matches, string input_file, string out_file, map<int,int> &colunms_filter,const int cols_count)
{
	ifstream file(input_file);
	ofstream res_file(out_file);
	
	string uni;
	getline(file,uni,'|');
	res_file << uni+"|"+getRow(file, colunms_filter, cols_count);
	while(file)
	{
		getline(file,uni,'|');
		if(matches[uni])
			res_file << uni+"|"+getRow(file, colunms_filter, cols_count), matches[uni]++;
		else
			getline(file, uni);
	}

	res_file.close();
	file.close();	
}

void buildFileIES(map<string,int> &matches, string input_file, string out_file, map<int,int> &colunms_filter,const int cols_count)
{
	ifstream file(input_file);
	ofstream res_file(out_file);

	string uni;
	getline(file,uni,'|');	
	res_file << uni+"|"+getRow(file, colunms_filter, cols_count);
	while(file)
	{
		getline(file,uni,'|');
		if(matches[uni])
			res_file << uni+"|"+getRow(file, colunms_filter, cols_count);
		else
			getline(file,uni);
	}

	res_file.close();
	file.close();	
}

void buildFileLocal(map<string,int> &matches, string input_file, string out_file, map<int,int> &colunms_filter,const int cols_count)
{
	ifstream file(input_file);
	ofstream res_file(out_file);
	
	string uni;
	getline(file,uni);
	res_file << uni+"\n";
	while(file)
	{
		string output = "";
		
		getline(file,uni,'|');
		output += uni+"|";
		getline(file,uni,'|');
		output += uni+"|";
		getline(file,uni,'|');
		output += uni+"|";
		
		if(matches[uni])
		{			
			getline(file,uni);
			output += uni;
			if(output[output.size()-1] == '|')
				output += " \n";
			else
				output += "\n";
			res_file << output;
		}
		else
			getline(file, uni);
	}

	res_file.close();
	file.close();	
}

int main()
{	
	map<string, int> matches;
	
	matches["2"] = 1;
	matches["575"] = 1;
	matches["1422"] = 1;
	matches["4"] = 1;
	matches["584"] = 1;
	matches["143"] = 1;
	matches["10"] = 1;
	matches["586"] = 1;
	matches["580"] = 1;
	matches["570"] = 1;
	
	
	fillFilter(aluno_colunms_vector, aluno_colunms_filter);
	fillFilter(ies_colunms_vector, ies_colunms_filter);
	fillFilter(docente_colunms_vector, docente_colunms_filter);
	fillFilter(local_colunms_vector, local_colunms_filter);
	fillFilter(curso_colunms_vector, curso_colunms_filter);
	
	buildFile(matches, aluno_src, aluno_out, aluno_colunms_filter, ALUNO_COLUNMS_COUNT);
	buildFileIES(matches, ies_src, ies_out, ies_colunms_filter, IES_COLUNMS_COUNT);
	buildFile(matches, curso_src, curso_out, curso_colunms_filter, CURSO_COLUNMS_COUNT);
	buildFile(matches, docente_src, docente_out, docente_colunms_filter, DOCENTE_COLUNMS_COUNT);
	buildFileLocal(matches, local_src, local_out, local_colunms_filter, LOCAL_COLUNMS_COUNT);
	
	vector<pair<string,int>> temp;
	
	for(map<string,int>::iterator it = matches.begin(); it != matches.end(); it++)
		if(it->second)
			temp.push_back(*it);
	
	sort(temp.begin(), temp.end(),
		[](pair<string, int>  lhs, pair<string, int> rhs) {return lhs.second > rhs.second;});
	
	for(vector<pair<string,int>>::iterator it = temp.begin(); it != temp.end(); it++)
		cout << it->first << " : " << it->second << endl;
	
	return 0;
}
